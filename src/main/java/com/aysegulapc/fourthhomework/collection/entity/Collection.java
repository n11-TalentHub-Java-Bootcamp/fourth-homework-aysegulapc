package com.aysegulapc.fourthhomework.collection.entity;

import com.aysegulapc.fourthhomework.debt.entity.Debt;
import com.aysegulapc.fourthhomework.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COLLECTION")
@Data
public class Collection implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Double amount;
    private Date createdDate;
    private Long userId;

    @ManyToOne
    @JoinColumn
    private Debt debt;
}
