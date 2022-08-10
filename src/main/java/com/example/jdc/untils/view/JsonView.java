package com.example.jdc.untils.view;

import com.example.jdc.untils.json.JsonHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/*视图响应，需要配置有 JsonHelper  */
public class JsonView {

    public static final String MEDIA_JSON_VIEW = "application/json;charset=utf-8";

    private String message;
    private boolean success;
    private Map<String,Object> content;

    private JsonView() {}

    public JsonView(String message, boolean success, Map<String, Object> content) {
        this.message = message;
        this.success = success;
        this.content = content;
    }

    public static JsonView success(String message) {
        return new JsonView(message,true,new HashMap<>());
    }
    public static JsonView failure(String message) {
        return new JsonView(message,false,new HashMap<>());
    }
    public JsonView put(String key,Object value) {
        content.put(key,value);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public void responseWebClient(HttpServletResponse response) {
        response.setContentType(MEDIA_JSON_VIEW);

        try {
            PrintWriter writer = response.getWriter();
            writer.write(JsonHelper.toJSON(this));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
