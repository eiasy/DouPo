package com.huayi.doupo.base.util.sdk.uc.service;

import java.util.HashMap;
import java.util.Map;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.util.sdk.uc.model.SDKException;
import com.huayi.doupo.base.util.sdk.uc.model.SessionInfo;

/**
 * 九游服务端有关的通信服务
 * <br>
 * <br>==========================
 */
public class SDKServerService extends AbstractSDKService{

    /**
     * 用户会话验证接口(ucid.user.sidInfo)
     * @param sid
     * @return
     * @throws SDKException 
     */
//    public static SidInfo verifySid(String sid) throws SDKException{
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("sid", sid);
//        SidInfo sidInfo = getSDKServerResponse(params, ServiceName.SS_PREFIX, ServiceName.SIDINFO_SERVICE, SidInfo.class);
//        return sidInfo;
//    }

    /**
     * 用户会话验证接口-支持统一账号标识(account.verifySession)
     * @param sid
     * @return
     * @throws SDKException 
     */
    public static SessionInfo verifySession(String sid, String gameId) throws SDKException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sid", sid);
        SessionInfo sessionInfo = getSDKServerResponse(params, ParamConfig.getSdkValue("uc", "prefix"), ParamConfig.getSdkValue("uc", "verifySession"), SessionInfo.class, gameId);
        return sessionInfo;
    }
    
    /**
     * 接收游戏服务器提交的游戏角色数据接口(ucid.game.gameData)
     * <br>
     * 请优先使用sdk客户端版接口，不推荐用服务器版接口
     * @param sid
     * @param gameData
     * @return
     * @throws SDKException 
     */
//    public static boolean gameData(String sid, UserGameData gameData) throws SDKException{
//        
//        if(gameData.getCategory() == null || "".equals(gameData.getCategory())){
//            throw new SDKException("游戏服务器角色数据类型为空", 10);
//        }
//        
//        //校验游戏参数-登录角色类型
//        if("loginGameRole".equals(gameData.getCategory())){
//            Set<String> keySet = gameData.getContent().keySet();
//            Set<String> categoriesSet = new HashSet<String>();
//            categoriesSet.add("roleLevel");  
//            categoriesSet.add("roleName");  
//            categoriesSet.add("zoneName");  
//            categoriesSet.add("roleId");  
//            categoriesSet.add("zoneId");  
//            
//            //必须含有所有关键字段
//            if(!keySet.containsAll(categoriesSet)){
//                throw new SDKException("游戏服务器角色数据有必填值为空", 10);
//            }
//        }
//        
//        
//        //转换游戏参数
//        String gameDataStr = null;
//        try {
//            gameDataStr = JacksonUtil.encode(gameData);
//            gameDataStr = URLEncoder.encode(gameDataStr, "utf-8");
//        } catch (Exception e) {
//            throw new SDKException("JsonEncode游戏服务器角色数据有误", e, -1);
//        }
//        
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("sid", sid);
//        params.put("gameData", gameDataStr);
//        String result = getSDKServerResponse(params, ServiceName.SS_PREFIX, ServiceName.GAMEDATA_SERVICE, String.class);
//        return "".equals(result) ? true : false;
//    }

}
