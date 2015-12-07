package com.huayi.doupo.logic.core.tcp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.socket.XmlDomBean;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.ReflectUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.logic.system.log.ThreadOper;
import com.huayi.doupo.base.util.logic.system.log.ThreadPoolUtils;
import com.huayi.doupo.logic.core.filter.netty.ChannelStateMap;
import com.huayi.doupo.logic.util.ChannelMapUtil;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.ProtocolHandleUtil;
import com.huayi.doupo.logic.util.SocketMapUtil;

/**
 * 服务器消息处理类 
 * @author mp
 * @date 2014-9-11 上午11:54:51
 */
public class GameServerHandler extends SimpleChannelInboundHandler<Object> {

	/**
	 * Channel激活
	 */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	LogUtil.out(DateUtil.getCurrTime() + "  " + ctx.channel().id().asLongText() + "-----channelActive--------");
    	ChannelMapUtil.add(ctx.channel());
//		ChannelHandlerContextMap.add(ctx, new); // 不需要在此add。
    }
    
    /**
     * 接受消息
     */
    @Override
	@SuppressWarnings("unchecked")
    public void messageReceived(final ChannelHandlerContext ctx, final Object msg) throws Exception {
    	
		final Map<String, Object> msgMap = (Map<String, Object>)msg;
		final int header = (int)msgMap.get("header");
		
		//封号/关服处理
		if (ParamConfig.closeServer) {
			MessageUtil.sendFailMsg(ctx.channel().id().asLongText(), msgMap, StaticCnServer.fail_closeServer);
			return;
		}
		
		//玩家状态0-离线, 1-在线  2-冻结
		Player player = PlayerMapUtil.getPlayerByChannelId(ctx.channel().id().asLongText());
		if (player != null) {
			if (player.getOnlineState() == 2) {
				MessageUtil.sendFailMsg(ctx.channel().id().asLongText(), msgMap, StaticCnServer.fail_frozen);
				return;
			}
		}
		
		//游戏逻辑
		if (header > 0 && header < 20000) {
			
//			System.out.println("------  " + Thread.currentThread().getName());
			//验证请求是否在有效时间内
		/*	if(header != 1001){
				Channel channel = ctx.channel();
				String channelId = channel.id().asLongText();
				Player player = PlayerMapUtil.getPlayerByChannelId(channelId);
				String operTime = player.getOperTime();
				if(operTime != null && !operTime.equals("") && DateUtil.getCurrMill() - DateUtil.getMillSecond(operTime) <= DictMapUtil.getSysConfigIntValue(StaticSysConfig.operMill)){
					//验证当天是否已经缠绵过
					MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notOper);
					return;
				}
				player.setOperTime(DateUtil.getCurrTime());
			}*/
			
			//验证重复发协议
/*			if (player != null) {
				
				//如果本次协议号和上次记录的协议号不一致
				if (header != player.getProtoHeader()) {
					player.setProtoHeader(header);//将协议号设置成本次协议号
					player.setOperTimeMill(DateUtil.getCurrMill());//设置成本次操作协议的时间
					player.setRepProtoCount(0);//将重复协议计数器置0
				} else {
					//当重复协议结束期大于等于某个数时,做验证,做完验证重置
					int startVerfNum = DictMapUtil.getSysConfigIntValue(StaticSysConfig.startVerfNum);
					int avgTimeInterval = DictMapUtil.getSysConfigIntValue(StaticSysConfig.avgTimeInterval);
//					System.out.println("startVerfNum = " + startVerfNum + "  avgTimeInterval = " + avgTimeInterval);
					if (player.getRepProtoCount() >= startVerfNum) {
						long currMill = DateUtil.getCurrMill();
						long playerMill = player.getOperTimeMill();
						long interval = currMill - playerMill;
						//求出单次协议间隔,如果此间隔小于某个数值,判定为重复刷协议,否则重置
//						System.out.println("-----" + (interval / player.getRepProtoCount()));
						if ((interval / player.getRepProtoCount()) < avgTimeInterval) {
							MessageData retBossMsgData = new MessageData();
							retBossMsgData.putStringItem("1", StaticCnServer.fail_operTooFast);
							MessageUtil.pushMsg(ctx.channel().id().asLongText(), StaticMsgRule.pushCloseServerData, retBossMsgData);
							ctx.channel().close();//断开连接
							LogUtil.info("重复发协议:instPlayerId=" + player.getPlayerId() + " name=" + player.getPlayerName() + " header=" + header);
						} else {
							player.setProtoHeader(header);//将协议号设置成本次协议号
							player.setOperTimeMill(DateUtil.getCurrMill());//设置成本次操作协议的时间
							player.setRepProtoCount(0);//将重复协议计数器置0
						}
					} else {
						//如果当次协议号和上次记录的协议号一样,计数器加1
						player.setRepProtoCount(player.getRepProtoCount() + 1);
					}
				}
//				System.out.println("header = " + player.getProtoHeader() + "  operTimeMill = " + player.getOperTimeMill() + " repCount = " + player.getRepProtoCount());
			}*/
			
			handler(ctx, header, msgMap);
			
		//GM逻辑不要影响游戏逻辑[GM的消息处理放到线程池中,线程池只存在1个工作线程]
		} else if (header >= 20000 && header < 30000) {
			
			boolean flag = false;
			Channel channel = ctx.channel();
			String channelId = channel.id().asLongText();
			
			String localAddress = channel.localAddress().toString();
			String gmPort = SysConfigUtil.getValue("socketGm.port");
			
			//只有连接Gm端口才能处理逻辑
			if(localAddress == null || localAddress.equals("")){
				flag = true;
			}else{
				if(!gmPort.equals(localAddress.split(":")[1])){
					flag = true;
				}
			}
			
			//除GM端口外的其他端口,不可处理GM逻辑
			if (flag) {
				MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
			} else {
				ThreadPoolUtils.execute(new ThreadOper() {
					@Override
					public void innerRun() {
						handler(ctx, header, msgMap);
					}
				});
			}
		}
    }
	
    /**
     * Channel关闭
     */
    public void channelInactive(ChannelHandlerContext ctx)throws Exception{
    	LogUtil.out(DateUtil.getCurrTime() + "  " + ctx.channel().id().asLongText() + "  --- Channel Close ---");
    	ChannelStateMap.remove(ctx);
    	ctx.channel().close();
    	SocketMapUtil.removeSocketMap(ctx.channel().id().asLongText());
    }
	
	/**
	 * 发生异常
	 */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		boolean flag = true;
		if (cause != null) {
			String errMsg = cause.toString();
			if (!("").equals(errMsg.trim())) {
				if (errMsg.contains("您的主机中的软件中止了一个已建立的连接") || errMsg.contains("远程主机强迫关闭了一个现有的连接") || errMsg.contains("Connection reset by peer") || errMsg.contains("Connection timed out")) {
					flag = false;
				}
			}
		}
		if (flag == true)
			LogUtil.error("Netty exceptionCaught : ", (Exception)cause);
    }
    
	/**
	 * 处理消息
	 * @author mp
	 * @date 2014-9-15 下午1:58:23
	 * @param ctx
	 * @param msg
	 * @Description
	 */
	private void handler (final ChannelHandlerContext ctx, final int header, final Map<String, Object> msgMap) {
		try {
			XmlDomBean bean = ProtocolHandleUtil.getXmlDomBean(header);
			String clazz = bean.getClazz();
			String method = bean.getMethod();
			Object[] prams = new Object[2];
			prams[0] = msgMap;
			prams[1] = ctx.channel().id().asLongText();
			
			ReflectUtil.invokeMethod(clazz, method, prams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
