package com.huayi.doupo.base.util.sdk.uc.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * JSON序列化和反序列化辅助类。
 * <br>
 * <br>==========================
 * <br> 公司：优视科技-游戏中心
 * <br> 开发：<a href="mailto:nieyong@ucweb.com">聂勇</a>
 * <br> 创建时间：2014-2-28下午4:21:03
 * <br>==========================
 */
public class Jackson {

    private ObjectMapper _objectMapper;
    
    private Jackson() {
        _objectMapper = new ObjectMapper();
        _objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        _objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }
    
    public static Jackson getInstance() {
        return new Jackson();
    }
    
    /**
     * 动态过滤指定的字段。
     * 
     * @param filterName 过滤器名称。为防止相互覆盖，请确保唯一性
     * @param properties 需要过滤的字段集
     * @return {@link Jackson}实例
     */
    public Jackson filter(String filterName, String... properties) {
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(properties));
        _objectMapper.setFilters(filterProvider);
        
        return this;
    }
    
    /**
     * 用一个新的类或接口来动态过滤指定的类。
     * 
     * @param targetClaz 待转换成JSON的类
     * @param mixInClaz 指定了过滤和转换规则的类或接口
     * @return {@link Jackson}实例
     */
    public Jackson mixInSerialization(Class<?> targetClaz, Class<?> mixInClaz) {
        _objectMapper.getSerializationConfig().addMixInAnnotations(targetClaz, mixInClaz);
        
        return this;
    }
    
    /**
     * 将JSON字符串转换成指定的Java对象。
     * 
     * @param json JSON字符串
     * @param claz 转换后的对象的Class
     * @return Java对象
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public <T> T json2Obj(String json, Class<T> claz) throws JsonParseException, 
            JsonMappingException, IOException {
        return _objectMapper.readValue(json, claz);
    }
    
    /**
     * 将JSON字符串转换成指定的Java对象。
     * 
     * @param json JSON字符串
     * @param typeRef 类型参考
     * @return Java对象
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public <T> T json2Obj(String json, TypeReference<T> typeRef) throws JsonParseException, 
            JsonMappingException, IOException {
        return _objectMapper.readValue(json, typeRef);
    }
    
    /**
     * 将Java对象转换成JSON字符串。
     * 
     * @param obj Java对象
     * @return JSON字符串
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String obj2Json(Object obj) throws JsonGenerationException, 
            JsonMappingException, IOException {
        return _objectMapper.writeValueAsString(obj);
    }

}
