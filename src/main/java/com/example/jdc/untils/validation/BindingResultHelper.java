package com.example.jdc.untils.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*数据校验 工具类 */
public class BindingResultHelper {
    public static Map<String,String> toErrorMap(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // new HashMap<>()生成map，用来存储错误数据，并返回
            Map<String,String> map = new HashMap<>();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : errorList) {
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return map;

        }
        //没有错误则返回一个空的map集合
        return Collections.emptyMap();
    }
}
