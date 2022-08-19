package com.example.jdc.untils.json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
/*数据转换 java <----> json*/
public final class JsonHelper {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /*java8 日期时间数据类型 规范*/
    static {
        OBJECT_MAPPER
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    private JsonHelper() {}

    /*java数据转换成Jackson数据*/
    public static String toJSON(Object object) {
        if (object == null) return "{}";

        try {
            String JSON = OBJECT_MAPPER.writeValueAsString(object);
            return JSON;
        } catch (JsonProcessingException e) {
            //运行时异常抛出
            throw new RuntimeException(e);
        }
    }

    /*Jackson数据转换成java数据*/
    public static <T> T toBean(String json,Class<T> clazz) {
        try {
            T bean = OBJECT_MAPPER.readValue(json, clazz);
            return bean;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
