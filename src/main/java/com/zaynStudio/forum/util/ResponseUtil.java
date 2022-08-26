package com.zaynStudio.forum.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ClassUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUtil {
    private boolean state;
    private Object result;

    public static ResponseUtil response(Object result){
        if(!result.getClass().equals(ErrorUtil.class))
            return new ResponseUtil(true,result);
        else
            return new ResponseUtil(false,((ErrorUtil)result).toString());
    }

}
