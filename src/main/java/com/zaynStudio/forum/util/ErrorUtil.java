package com.zaynStudio.forum.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorUtil {

    private String code;
    private String desc;

    @Override
    public String toString() {
        return code + ":" + desc;
    }

}
