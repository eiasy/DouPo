package com.huayi.doupo.base.util.sdk.uc.protocol;

import java.util.Map;

/**
 * 客户端请求服务端时使用
 * <br>
 * <br>========================== 
 * <br>公司：优视科技-游戏中心 
 * <br>开发：liusl@ucweb.com 
 * <br>创建时间：2015年4月17日
 * <br>==========================
 */
public class CpRequest {

    /**
     * 客户端请求id
     */
    private long id;

    /**
     * 服务名称
     */
    private String service;
    
    /**
     * 加密方式 （md5)
     */
    private String encrypt;

    /**
     * 签名
     */
    private String sign;


    /**
     * 游戏信息，CP会携带
     */
    private Game game;

    /**
     * 业务数据（各个接口不同，具体结构可查看对应的接口文档）
     */
    private Map<String, Object> data;
    
    private Map<String, Object> client;

    /**
     * 游戏基本信息
     */
    public class Game {
        // cp编号
        private int cpId = 0;
        // 游戏编号
        private int gameId = 0;
        // 游戏发行id
        private String channelId = "";
        // 游戏服务器id
        private int serverId = 0;
        // 游戏服务器名称
        private String serverName = "";

        public int getCpId() {
            return cpId;
        }

        public void setCpId(int cpId) {
            this.cpId = cpId;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public int getServerId() {
            return serverId;
        }

        public void setServerId(int serverId) {
            this.serverId = serverId;
        }

        public String getServerName() {
            return serverName;
        }

        public void setServerName(String serverName) {
            this.serverName = serverName;
        }
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String, Object> getClient() {
        return client;
    }

    public void setClient(Map<String, Object> client) {
        this.client = client;
    }
    
}
