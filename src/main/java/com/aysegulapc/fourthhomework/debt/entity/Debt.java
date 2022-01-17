package com.aysegulapc.fourthhomework.debt.entity;

import com.aysegulapc.fourthhomework.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DEBT")
@Data
public class Debt implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private double mainDebt;
    private double remainingDebt;
    private Date expiryDate;
    private String debtType;
    private Long topDebtId;
}
