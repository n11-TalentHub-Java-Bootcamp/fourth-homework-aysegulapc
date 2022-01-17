package com.aysegulapc.fourthhomework.usr.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
