package com.huayi.doupo.base.util.sdk.uc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

/**
 * 初始化配置类
 * <br>==========================
 */
public class Configuration {
    private static Properties defaultProperty;
    
    private static boolean loaded = false;

    static {
        init();
    }

    static void init() {
        defaultProperty = new Properties();
        defaultProperty.setProperty("sdkserver.debug", "false");
        //defaultProperty.setProperty("sdkserver.http.proxyHost","");
        //defaultProperty.setProperty("sdkserver.http.proxyUser","");
        //defaultProperty.setProperty("sdkserver.http.proxyPassword","");
        //defaultProperty.setProperty("sdkserver.http.proxyPort","");
        defaultProperty.setProperty("sdkserver.http.maxConnectionsPerHost", "150");
        defaultProperty.setProperty("sdkserver.http.connectionTimeout", "30000");
        defaultProperty.setProperty("sdkserver.http.readTimeout", "30000");
        
        defaultProperty.setProperty("sdkserver.baseUrl", "http://sdk.g.uc.cn");
        defaultProperty.setProperty("domainserver.cm.baseUrl", "http://183.233.224.202:8080");
        defaultProperty.setProperty("domainserver.ct.baseUrl", "http://119.147.224.168:8080");
        defaultProperty.setProperty("domainserver.unicom.baseUrl", "http://163.177.128.251:8080");
        
        defaultProperty.setProperty("sdkserver.clientVersion", Version.getVersion());

        String sdk4jProps = "config.properties";
        boolean loaded = loadProperties(defaultProperty, "." + File.separatorChar + sdk4jProps) ||
                loadProperties(defaultProperty, Configuration.class.getResourceAsStream("/WEB-INF/" + sdk4jProps)) ||
                loadProperties(defaultProperty, Configuration.class.getResourceAsStream("/" + sdk4jProps));
        Configuration.loaded = loaded;
    }
    
    /**
     * 自定义配置文件的路径，加载完即标记为已加载状态
     * @param path
     * @return
     */
    public static boolean loadProperties(String path){
        try {
            File file = new File(path);
            if(file.exists() && file.isFile()){
                defaultProperty.load(new FileInputStream(file));
                Configuration.loaded = true;
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
    }
    
    /**
     * 自定义配置文件的文件流，加载完即标记为已加载状态
     * @param is
     * @return
     */
    public static boolean loadProperties(InputStream is){
        try {
            defaultProperty.load(is);
            Configuration.loaded = true;
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }
    
    /**
     * 手动设置参数值，所有的参数完毕后，需要调用markLoaded()方法来标记设置值已完毕
     * @see #markLoaded
     * @param key
     * @param value
     * @return
     */
    public static boolean setProperty(String key, String value){
        try{
            defaultProperty.setProperty(key, value);
        }
        catch (Exception ignore) {
        }
        return false;
    }
    
    /**
     * 属性文件加载成功后，最后一步：标记已完成
     */
    public static void markLoaded(){
        //标示属性文件已加载成功
        Configuration.loaded = true;
    }

    private static boolean loadProperties(Properties props, String path) {
        try {
            File file = new File(path);
            if(file.exists() && file.isFile()){
                props.load(new FileInputStream(file));
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
    }

    private static boolean loadProperties(Properties props, InputStream is) {
        try {
            props.load(is);
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    public static String getProxyHost() {
        checkLoadProperty();
        return getProperty("sdkserver.http.proxyHost");
    }

    public static String getProxyHost(String proxyHost) {
        checkLoadProperty();
        return getProperty("sdkserver.http.proxyHost", proxyHost);
    }

    public static int getProxyPort() {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.proxyPort");
    }
    
    public static int getProxyPort(int port) {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.proxyPort", port);
    }
    
    public static String getProxyUser() {
        checkLoadProperty();
        return getProperty("sdkserver.http.proxyUser");
    }

    public static String getProxyUser(String user) {
        checkLoadProperty();
        return getProperty("sdkserver.http.proxyUser", user);
    }
    
    public static String getProxyPassword() {
        checkLoadProperty();
        return getProperty("sdkserver.http.proxyPassword");
    }

    public static String getProxyPassword(String password) {
        checkLoadProperty();
        return getProperty("sdkserver.http.proxyPassword", password);
    }

    public static int getDefaultMaxConnectionsPerHost() {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.maxConnectionsPerHost");
    }
    
    public static int getDefaultMaxConnectionsPerHost(int maxConnectionsPerHost) {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.maxConnectionsPerHost", maxConnectionsPerHost);
    }
    
    public static int getConnectionTimeout() {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.connectionTimeout");
    }
    
    public static int getConnectionTimeout(int connectionTimeout) {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.connectionTimeout", connectionTimeout);
    }

    public static int getReadTimeout() {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.readTimeout");
    }

    public static int getReadTimeout(int readTimeout) {
        checkLoadProperty();
        return getIntProperty("sdkserver.http.readTimeout", readTimeout);
    }

    public static boolean getBoolean(String name) {
        String value = getProperty(name);
        return Boolean.valueOf(value);
    }

    public static int getIntProperty(String name) {
        String value = getProperty(name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static int getIntProperty(String name, int fallbackValue) {
        String value = getProperty(name, String.valueOf(fallbackValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static long getLongProperty(String name) {
        String value = getProperty(name);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static String getProperty(String name) {
        return getProperty(name, null);
    }

    public static String getProperty(String name, String fallbackValue) {
        checkLoadProperty();
        String value;
        try {
            value = System.getProperty(name, fallbackValue);
            if (null == value) {
                value = defaultProperty.getProperty(name);
            }
        } catch (AccessControlException ace) {
            // Unsigned applet cannot access System properties
            value = fallbackValue;
        }
        return replace(value);
    }

    private static String replace(String value) {
        if (null == value) {
            return value;
        }
        String newValue = value;
        int openBrace = 0;
        if (-1 != (openBrace = value.indexOf("{", openBrace))) {
            int closeBrace = value.indexOf("}", openBrace);
            if (closeBrace > (openBrace + 1)) {
                String name = value.substring(openBrace + 1, closeBrace);
                if (name.length() > 0) {
                    newValue = value.substring(0, openBrace) + getProperty(name)
                            + value.substring(closeBrace + 1);

                }
            }
        }
        if (newValue.equals(value)) {
            return value;
        } else {
            return replace(newValue);
        }
    }

    public static boolean getDebug() {
        checkLoadProperty();
        return getBoolean("sdkserver.debug");

    }

    /**
     * 游戏相关信息获取配置项
     * @return
     */
    public static int getCpId() {
        checkLoadProperty();
        return getIntProperty("sdkserver.game.cpId");
    }

    public static String getChannelId() {
        checkLoadProperty();
        return getProperty("sdkserver.game.channelId");
    }

    public static int getGameId() {
        checkLoadProperty();
        Integer gameId =  getIntProperty("sdkserver.game.gameId");
        if(gameId == null || gameId <= 0){
            throw new RuntimeException("gameId必须完成初始化配置");
        }
        return gameId;
    }

    public static int getServerId() {
        checkLoadProperty();
        return getIntProperty("sdkserver.game.serverId");
    }

    public static String getServerName() {
        checkLoadProperty();
        return getProperty("sdkserver.game.serverName");
    }

    public static String getGameApiKey() {
        checkLoadProperty();
        String apiKey = getProperty("sdkserver.game.apikey");
        if(apiKey == null || "".equals(apiKey)){
            throw new RuntimeException("apiKey必须完成初始化配置");
        }
        return apiKey;
    }
    
    public static String getSDKServerBaseUrl() {
        checkLoadProperty();
        return getProperty("sdkserver.baseUrl");
    }
    
    public static String getDomainBaseUrlWithCm() {
        checkLoadProperty();
        return getProperty("domainserver.cm.baseUrl");
    }
    
    public static String getDomainBaseUrlWithCt() {
        checkLoadProperty();
        return getProperty("domainserver.ct.baseUrl");
    }
    
    public static String getDomainBaseUrlWithUnicom() {
        checkLoadProperty();
        return getProperty("domainserver.unicom.baseUrl");
    }
    
    private static void checkLoadProperty(){
        if(!loaded){
            throw new RuntimeException("配置文件未初始化");
        }
    }
}
