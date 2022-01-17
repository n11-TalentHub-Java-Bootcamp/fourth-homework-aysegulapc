package com.aysegulapc.fourthhomework.usr.dto;

import lombok.Data;

@Data
public class UserSaveRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
