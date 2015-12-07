package com.huayi.doupo.base.util.sdk.uc.constant;

public class ServiceName {
    
    /**
     * 老接口全部使用ss的内部域
     */
    public static final String SS_PREFIX = "ss/";

    /**
     * 新接口使用cp的内部域
     */
    public static final String CP_PREFIX = "cp/";

    /**
     * 天雷系统的服务名
     */
    public static final String DOMAINSERVER_SERVICE = "/httpdns/request";
    
    /**
     * 天雷系统的请求报文(参数指定)
     */
    public static final String DOMAINSERVER_REQ_BODY = "1|sdk.g.uc.cn";
    
    /**
     * 天雷系统的本地缓存KEY
     */
    public static final String DOMAINSERVER_CACHE_KEY = "domain_server_cache_key";
    
    /**
     * 天雷系统的并发控制KEY
     */
    public static final String DOMAINSERVER_LOCK_KEY = "domain_server_lock_key";
    
    /**
     * 天雷系统的本地属性KEY
     */
    public static final String DOMAINSERVER_ATTR_KEY = "domain_server_attr_key";
    
    /**
     * UCID绑定接口
     */
    public static final String SIDINFO_SERVICE = "ucid.user.sidInfo";
    public static final String VERIFYSESSION_SERVICE = "account.verifySession";
    public static final String GAMEDATA_SERVICE = "ucid.game.gameData";
    
}
