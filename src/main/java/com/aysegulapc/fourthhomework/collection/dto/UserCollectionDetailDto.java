package com.aysegulapc.fourthhomework.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserCollectionDetailDto {
    private Long id;
    private Double amount;
    private Date createdDate;
    private Long userId;
    private String firstName;
    private String lastName;
}
