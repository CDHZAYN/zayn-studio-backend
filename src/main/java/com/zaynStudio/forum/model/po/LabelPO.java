package com.zaynStudio.forum.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelPO {
    private BigDecimal labelID;
    private BigDecimal labelName;
}
