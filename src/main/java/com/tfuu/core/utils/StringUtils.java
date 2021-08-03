package com.tfuu.core.utils;

public class StringUtils {

    // Empty
    /**
     * 字符串是否为空，空的定义如下:<br>
     * 1、为null <br>
     * 2、为""<br>
     *
     * @param str
     *            被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String defaultIfIsEmpty(final String cs,
                                          final String defaultValue) {
        return isEmpty(cs) ? defaultValue : cs;
    }

}
