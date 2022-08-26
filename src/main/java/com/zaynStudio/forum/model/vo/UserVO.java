package com.zaynStudio.forum.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String userName;
    private String email;
    private String avatar;
    private String introduction;
    private BigDecimal blogNum;
    private String job;
}
