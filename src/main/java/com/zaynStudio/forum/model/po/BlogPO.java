package com.zaynStudio.forum.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPO {
    private BigDecimal blogID;
    private BigDecimal userID;
    private String date;
    private BigDecimal markNum;
    private boolean isStarred;
    private String labels;
    private String markdown;
}
