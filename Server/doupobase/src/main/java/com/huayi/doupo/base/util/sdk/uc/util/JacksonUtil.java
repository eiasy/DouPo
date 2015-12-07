package com.huayi.doupo.base.util.sdk.uc.util;

import org.codehaus.jackson.type.TypeReference;

/**
 * JSON序列化／反序列化常用方法类。
 * 
 * <br>========================== 
 * <br>公司：优视科技 
 * <br>开发：liuyong@ucweb.com， <a href="mailto:nieyong@ucweb.com">聂勇</a>
 * <br>创建时间：2012-2-28下午11:06:59 
 * <br>========================== 
 * <br>修改记录：
 * <pre>
 * 2013-06-18 NieYong 从SDKServer2.4.11版本重构而来并补充注释
 * 2014-02-28 NieYong 重写各方法内部实现并增加过滤字段功能
 * </pre>
 */
public class JacksonUtil {

    private static Jackson _jackson = Jackson.getInstance();
    
    /**
     * 将JSON字符串根据指定的Class反序列化成Java对象。
     * 
     * @param json JSON字符串
     * @param pojoClass Java对象Class
     * @return 反序列化生成的Java对象
     * @throws Exception 如果反序列化过程中发生错误，将抛出异常
     */
    public static Object decode(String json, Class<?> pojoClass)
            throws Exception {
        return _jackson.json2Obj(json, pojoClass);
    }
    /**
     * 将JSON字符串根据指定的Class反序列化成Java对象。
     * 
     * @param json JSON字符串
     * @param reference 类型引用
     * @return 反序列化生成的Java对象
     * @throws Exception 如果反序列化过程中发生错误，将抛出异常
     */
	public static Object decode(String json, TypeReference<?> reference) 
	        throws Exception {
	    return _jackson.json2Obj(json, reference);
	}

    /**
     * 将Java对象序列化成JSON字符串。
     * 
     * @param obj 待序列化生成JSON字符串的Java对象
     * @return JSON字符串
     * @throws Exception 如果序列化过程中发生错误，将抛出异常 
     */
    public static String encode(Object obj) throws Exception {
        return _jackson.obj2Json(obj);
    }
    
    /**
     * 将Java对象序列化成JSON字符串。<strong>注：</strong>使用此方法需要在类上用注解"@JsonFilter("filterName")"，否则过滤无效。
     * @param obj 待序列化生成JSON字符串的Java对象
     * @param filterName 过滤器名称。需确保唯一性，以免相互覆盖导致错误过滤字段。
     * @param properties 需过滤的字段集
     * @return JSON字符串
     * @throws Exception 如果序列化过程中发生错误，将抛出异常 
     */
    public static String encode(Object obj, String filterName, 
            String... properties) throws Exception {
        Jackson jackson = Jackson.getInstance().filter(filterName, properties);
        return jackson.obj2Json(obj);
    }
    
    /**
     * 用一个新的类或接口来动态过滤指定的类。
     * 
     * @param obj 待转换成JSON的对象
     * @param mixInSource 指定了过滤和转换规则的类或接口
     * @return JSON字符串
     * @throws Exception 如果序列化过程中发生错误，将抛出异常
     */
    public static String encode(Object obj, Class<?> mixInSource) throws Exception {
        Jackson jackson = Jackson.getInstance().mixInSerialization(obj.getClass(), mixInSource);
        return jackson.obj2Json(obj);
    }
    
    /**
     * 用一个新的类或接口来动态过滤指定的类。<strong>注：</strong>可用于过滤对象中的子类。
     * 
     * @param obj 待转换成JSON的对象
     * @param mixInTarget 使用过滤规则的类。可以是<code>obj</code>或者是<code>obj</code>的子类。
     * @param mixInClaz 指定了过滤和转换规则的类或接口
     * @return JSON字符串
     * @throws Exception 如果序列化过程中发生错误，将抛出异常
     */
    public static String encode(Object obj, Class<?> mixInTarget, Class<?> mixInSource) throws Exception {
        Jackson jackson = Jackson.getInstance().mixInSerialization(mixInTarget, mixInSource);
        return jackson.obj2Json(obj);
    }

}
