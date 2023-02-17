package com.tfuu.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private JsonUtils() {
    }

    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String toJSONString(final T t) {
        String json = "";
        try {
            json = mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            
        }
        return json;
    }
}
