package com.tfuu.core.model;
/**
 *
 */

import com.fasterxml.jackson.annotation.JsonView;
import com.tfuu.core.utils.DataUtils;
import com.tfuu.core.utils.DateUtils;

import java.util.*;

/**
 * @author dtsola<br/>
 *         描述：参数生成类<br/>
 */
@SuppressWarnings("all")
@JsonView(ResultView.class)
public class Parameters<T> extends HashMap<String, T> implements JsonString {
    private static final long serialVersionUID = 1L;

    public Parameters() {
    }

    public Parameters append(String key, T value) {
        put(key, value);
        return this;
    }

    public Parameters appendAll(Map map) {
        putAll(map);
        return this;
    }

    public String getString(String key) {
        return DataUtils.toString(get(key), "");
    }

    public int getInt(String key) {
        return DataUtils.toInteger(get(key), 0);
    }

    public short getShort(String key) {
        return DataUtils.toShort(get(key), (short) 0);
    }

    public long getLong(String key) {
        return DataUtils.toLong(get(key), 0L);
    }

    public boolean getBoolean(String key) {
        return DataUtils.toBoolean(get(key), false);
    }

    public byte getByte(String key) {
        return DataUtils.toByte(get(key), (byte) 0);
    }

    public Date getDate(String key) {
        return DataUtils.toDate(get(key), DateUtils.getCurrent());
    }

    public List getList(String key) {
        final List r = new ArrayList();
        final Object obj = get(key);
        if (obj != null && obj instanceof List) {
            r.addAll((List) obj);
        }
        return r;
    }

    public Parameters getParameters(String key) {
        final Parameters r = Parameters.create();
        final Object obj = get(key);
        if (obj != null && obj instanceof Map) {
            r.appendAll((Map) obj);
        }
        return r;
    }

    public List<Parameters> getParametersList(String key) {
        final List<Parameters> r = new ArrayList<>();
        final Object obj = get(key);
        if (obj != null && obj instanceof List) {
            final List l = (List) obj;
            for (Object row : l) {
                if (row != null && row instanceof Map) {
                    r.add(Parameters.create().appendAll((Map) row));
                }
            }
        }
        return r;
    }

    public static final <T> Parameters<T> create() {
        return new Parameters<T>();
    }

}

