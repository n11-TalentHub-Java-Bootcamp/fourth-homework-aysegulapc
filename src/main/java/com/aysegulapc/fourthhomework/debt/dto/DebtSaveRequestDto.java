package com.aysegulapc.fourthhomework.debt.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DebtSaveRequestDto {
    private Long id;

    private Long userId;
    private double mainDebt;
    private double remainingDebt;
    private Date expiryDate;
    private String debtType;
    private Long topDebtId;
}
