package com.aysegulapc.fourthhomework.usr.entity;

import com.aysegulapc.fourthhomework.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
public class User implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
