package com.huayi.doupo.logic.core.http;
 
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Charsets;
import com.huayi.doupo.base.util.base.JsonUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.RechargeUtil;
 
/**
 * 充值处理Handler
 * @author mp
 * @date 2015-7-13 下午6:15:37
 */
public class RechargeServerHandler extends SimpleChannelInboundHandler<HttpObject> {
 
	private String uri;
	
	private String contentType = "";
 
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }
 
    /**
     * 接受消息
     */
    public void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
    	
    	//首次请求[url]
        if (msg instanceof HttpRequest) {
        	
            HttpRequest request = (HttpRequest) msg;
			String clientIP = request.headers().get("X-Forwarded-For");
			if (clientIP == null) {
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
						.remoteAddress();
				clientIP = insocket.getAddress().getHostAddress();
			}
			LogUtil.out("========Client IP =========" + clientIP);
            
            
            uri = request.getUri();
            if (uri.equals("/favicon.ico")) {
				return;
			}
            contentType(request);
//          System.out.println(params(uri, false));
        }
        
        //再次请求[body]
        if (msg instanceof HttpContent) {
        	LogUtil.out("========HttpContent Body Content=========");
        	HttpContent chunk = (HttpContent) msg;
        	ByteBuf buf = chunk.content();
        	String params = buf.toString(Charsets.UTF_8);
        	
        	System.out.println("xxxx :" + params);
        	
        	
        	
        	Map<String, String> mapMap = new HashMap<>();
    		String [] uriArray = params.split("&");
    		for (String inner : uriArray) {
    			String name = inner.split("=")[0];
    			String value = "";
    			if (name.equals("rolename")) {
    				value = new String(inner.split("=")[1].getBytes("iso-8859-1"), "utf-8");
    				System.out.println("ppppppppp  " + value);
				}
    			mapMap.put(name, value);
    		}
    		System.out.println(mapMap);
        	
        	
        	
        	
        	
        	
        	
        	
        	System.out.println("......." + params);
        	if (contentType.equals("application/json")) {
        		//params is the json
			} else {
				if (uri.equals("/30000")) {
					try {
						Map<String, String> paramsMap = RechargeUtil.params(params, true);
						LogUtil.out("=======Parmas = " + paramsMap);
//						RechargeUtil.recharge(ctx.channel(), paramsMap);
					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.error("请求参数异常", e);
						
						Map<String, String> retMap = new HashMap<String, String>();
						retMap.put("code", "5");
						retMap.put("message", "请求参数异常");
						RechargeUtil.writeResponse(ctx.channel(), JsonUtil.toJson(retMap));
					}
				}
			}
        }
        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
    
    
    /**
     * 设置内容类型
     * @author mp
     * @date 2015-7-13 下午3:41:56
     * @param request
     * @Description
     */
    private void contentType (HttpRequest request) {
        for (Entry<String, String> entry : request.headers()) {
        	if (entry.getKey().equals("Content-Type")) {
        		contentType = entry.getValue();
			}
        }
    }
 
}