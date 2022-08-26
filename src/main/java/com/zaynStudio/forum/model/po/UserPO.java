package com.zaynStudio.forum.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {
    private BigDecimal userID;
    private String userName;
    private String email;
    private String password;
    private String avatar;
    private String introduction;
    private BigDecimal blogNum;
    private Integer job;
}
