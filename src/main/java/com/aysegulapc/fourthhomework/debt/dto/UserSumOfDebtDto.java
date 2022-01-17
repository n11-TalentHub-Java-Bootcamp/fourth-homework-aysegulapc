package com.aysegulapc.fourthhomework.debt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSumOfDebtDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Double sumOfDebt;
}
