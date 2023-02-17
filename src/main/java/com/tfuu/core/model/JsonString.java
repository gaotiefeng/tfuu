package com.tfuu.core.model;

import com.tfuu.core.utils.JsonUtils;

import java.io.Serializable;

/**
 *  表明是一个可以转换JSON字符串的对象<br/>
 */
public interface JsonString extends Serializable {

    /**
     * 描述：返回JSON字符串
     */
    default String toJsonString() {
        return toJsonString(this);
    }

    /**
     * 描述：返回JSON字符串
     */
    default String toJsonString(final Object src) {
        return JsonUtils.toJSONString(src);
    }

}
