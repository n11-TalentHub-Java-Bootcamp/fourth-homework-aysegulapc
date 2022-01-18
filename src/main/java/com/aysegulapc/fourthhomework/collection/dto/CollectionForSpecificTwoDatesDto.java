package com.aysegulapc.fourthhomework.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CollectionForSpecificTwoDatesDto {
    private Long id;
    private Double amount;
    private Date createdDate;
    private Long userId;
}
