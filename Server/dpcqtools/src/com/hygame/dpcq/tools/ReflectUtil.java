package com.hygame.dpcq.tools;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 反射工具类,现有方法:
 * 
 * 1.得到某个对象的公共属性: Object getProperty(Object owner, String fieldName) 
 * 2.利用反射调用方法（根据实例对象）:Object invokeMethod(Object owner, String methodName, Class[] argsType , Object[] argsValue)
 * 3.利用反射调用方法（根据类名）:Object invokeMethod(String className, String methodName, Class[] argsType , Object[] argsValue)
 * 4.利用java反射和构造方法生成实例：Object newInstance(String className, Class[] argsType, Object[] argsValue)
 * 5.是不是某个类的实例: boolean isInstance(Object obj, Class cls)
 * 6.得到数组中的某个元素: Object getByArray(Object array, int index) 
 * @since 2011-12-26
 * @version 0.1
 */

public class ReflectUtil {
   
	/**
     * 得到某个对象的公共属性
     * @param owner, fieldName
     * @return 该属性对象
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static Object getProperty(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(owner);
        return property;
    }

    /**
     * 得到某类的静态公共属性    [没有多大用处，自我感觉]
     * @param className   类名
     * @param fieldName   属性名
     * @return 该属性对象
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static Object getStaticProperty(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Object obj = ownerClass.newInstance();
        Field field = ownerClass.getField(fieldName);
        field.set(obj, "miao  peng");
        Object property = field.get(obj);
        return property;
    }


    /**
     * 利用反射调用方法（根据实例对象）
     * @param owner  实例对象
     * @param methodName  方法名
     * @param argsType  参数类型
     * @param argsValue  参数值
     * @return 方法的返回值
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object invokeMethod(Object owner, String methodName, Class[] argsType , Object[] argsValue) throws Exception {
        Class ownerClass = owner.getClass();
        Method method = ownerClass.getDeclaredMethod(methodName, argsType);
        method.setAccessible(true);
        return method.invoke(owner, argsValue);
    }
    
    /**
     * 利用反射调用方法（根据类名）
     * @param className  类名
     * @param methodName  方法名
     * @param argsType  参数类型
     * @param argsValue  参数值
     * @return 
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object invokeMethod(String className, String methodName, Class[] argsType , Object[] argsValue) throws Exception {
        Class ownerClass = Class.forName(className);
        Object owner = ownerClass.newInstance();
        Method method = ownerClass.getDeclaredMethod(methodName, argsType);
        method.setAccessible(true);   
        return method.invoke(owner, argsValue);
    }

   /**
    * 利用java反射和构造方法生成实例
    * @param className 类名
    * @param argsType 参数类型
    * @param argsValue 参数值
    * @return
    * @throws Exception
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object newInstance(String className, Class[] argsType, Object[] argsValue) throws Exception {
        Class newoneClass = Class.forName(className);
        Constructor cons = newoneClass.getConstructor(argsType);
        return cons.newInstance(argsValue);
    }
    
    /**
     * 是不是某个类的实例
     * @param obj 实例
     * @param cls 类
     * @return 如果 obj 是cls类的实例，则返回 true
     */
    @SuppressWarnings("rawtypes")
	public static boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }
    
    /**
     * 得到数组中的某个元素
     * @param array 数组
     * @param index 索引
     * @return 返回指定数组对象中索引组件的值
     */
    public static Object getByArray(Object array, int index) {
        return Array.get(array,index);
   }
}