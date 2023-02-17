package com.tfuu.core.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Bean工具类
 *
 * <p>
 * 把一个拥有对属性进行set和get方法的类，我们就可以称之为JavaBean。
 * </p>
 *
 * @author Looly
 * @since 3.1.2
 */
public class BeanUtils2 {

    /**
     * 判断是否为Bean对象，判定方法是：
     *
     * <pre>
     *     1、是否存在只有一个参数的setXXX方法
     *     2、是否存在public类型的字段
     * </pre>
     *
     * @param clazz
     *            待测试类
     * @return 是否为Bean对象
     * @see #hasSetter(Class)
     */
    public static boolean isBean(Class<?> clazz) {
        return hasSetter(clazz) || hasPublicField(clazz);
    }

    /**
     * 判断是否有Setter方法<br>
     * 判定方法是是否存在只有一个参数的setXXX方法
     *
     * @param clazz
     *            待测试类
     * @return 是否为Bean对象
     * @since 4.2.2
     */
    public static boolean hasSetter(Class<?> clazz) {
        if (ClassUtils.isNormalClass(clazz)) {
            final Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getParameterTypes().length == 1
                        && method.getName().startsWith("set")) {
                    // 检测包含标准的setXXX方法即视为标准的JavaBean
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 指定类中是否有public类型字段(static字段除外)
     *
     * @param clazz
     *            待测试类
     * @return 是否有public类型字段
     * @since 5.1.0
     */
    public static boolean hasPublicField(Class<?> clazz) {
        if (ClassUtils.isNormalClass(clazz)) {
            for (Field field : clazz.getFields()) {
                if (ModifierUtils.isPublic(field)
                        && false == ModifierUtils.isStatic(field)) {
                    // 非static的public字段
                    return true;
                }
            }
        }
        return false;
    }
}
