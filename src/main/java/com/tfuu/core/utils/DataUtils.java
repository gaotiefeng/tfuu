package com.tfuu.core.utils;

import com.tfuu.core.model.JsonString;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dtsola<br/>
 *         描述：数据类型转换工具类<br/>
 */
@SuppressWarnings("all")
public final class DataUtils {

    private DataUtils() {
    }

    public static boolean toBoolean(final Object value,
                                    final boolean defaultValue) {
        boolean r = defaultValue;
        try {
            if (value != null) {
                if (value instanceof Boolean)
                    r = (Boolean) value;
                else
                    r = Boolean.parseBoolean(value + "");
            }
        } catch (Exception e) {
        }
        return r;

    }

    public static double toDouble(final Object value, final double defaultValue) {
        double r = defaultValue;
        try {
            if (value != null)
                if (value instanceof Double)
                    r = (Double) value;
                else
                    r = Double.parseDouble(value + "");
        } catch (Exception e) {
        }
        return r;
    }

    public static float toFloat(final Object value, final float defaultValue) {
        float r = defaultValue;
        try {
            if (value != null)
                if (value instanceof Float)
                    r = (Float) value;
                else
                    r = Float.parseFloat(value + "");
        } catch (Exception e) {
        }
        return r;
    }

    public static long toLong(final Object value, final long defaultValue) {
        long r = defaultValue;
        try {
            if (value != null)
                if (value instanceof Long)
                    r = (Long) value;
                else
                    r = Long.parseLong(value + "");
        } catch (Exception e) {
        }
        return r;
    }

    public static byte toByte(final Object value, final byte defaultValue) {
        byte r = defaultValue;
        try {
            if (value != null)
                if (value instanceof Byte)
                    r = (Byte) value;
                else
                    r = Byte.parseByte(value + "");
        } catch (Exception e) {
        }
        return r;
    }

    public static short toShort(final Object value, final short defaultValue) {
        short r = defaultValue;
        try {
            if (value != null)
                if (value instanceof Short)
                    r = (Short) value;
                else
                    r = Short.parseShort(value + "");
        } catch (Exception e) {
        }
        return r;
    }

    public static int toInteger(final Object value, final int defaultValue) {
        int r = defaultValue;
        try {
            if (value != null)
                if (value instanceof Integer)
                    r = (Integer) value;
                else
                    r = Integer.parseInt(value + "");
        } catch (Exception e) {
        }
        return r;
    }

    public static String toString(final Object value, final String defaultValue) {
        String r = defaultValue;
        if (value != null)
            if (value instanceof String)
                r = (String) value;
            else if (value instanceof JsonString)
                r = ((JsonString) value).toJsonString();
            else if (value instanceof Map)
                r = JsonUtils.toJSONString(value);
            else if(BeanUtils2.isBean(value.getClass()))
                r = JsonUtils.toJSONString(value);
            else
                r = value + "";
        return r;
    }

    public static Date toDate(final Object value, final Date defaultValue) {
        Date r = defaultValue;
        if (value != null)
            if (value instanceof Date)
                r = (Date) value;
        return r;
    }

    public static StringBuilder toStringBuilder(final Object value,
                                                final String defaultValue) {
        final StringBuilder r = new StringBuilder();
        if (value != null)
            if (value instanceof StringBuilder)
                r.append(value);
            else
                r.append(ObjectUtils.defaultIfNull(value, defaultValue));
        else if (defaultValue != null)
            r.append(defaultValue);
        return r;
    }

    public static StringBuilder toStringBuilders(final List<Object> values,
                                                 final String defaultValue) {
        final StringBuilder r = new StringBuilder();
        if (values != null && values.size() > 0) {
            values.forEach(value -> r.append(ObjectUtils.defaultIfNull(value,
                    "")));
        } else if (defaultValue != null)
            r.append(defaultValue);
        return r;
    }

    public static String toStrings(final List<Object> values,
                                   final String defaultValue) {
        return toStringBuilders(values, defaultValue).toString();
    }

}

