package com.aysegulapc.fourthhomework.debt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDebtDetailDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String debtType;
    private Double mainDebt;
    private Double remainingDebt;
    private Date expiryDate;
}
