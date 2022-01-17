package com.aysegulapc.fourthhomework.collection.dto;

import com.aysegulapc.fourthhomework.debt.entity.Debt;
import lombok.Data;

import java.util.Date;

@Data
public class CollectionSaveRequestDto {
    private Long id;

    private Double amount;
    private Date createdDate;
    private Debt debt;
    private Long userId;
}
