package com.aysegulapc.fourthhomework.usr.controller;

import com.aysegulapc.fourthhomework.usr.dto.UserDto;
import com.aysegulapc.fourthhomework.usr.dto.UserSaveRequestDto;
import com.aysegulapc.fourthhomework.usr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity getAll() {
        List<UserDto> userDtoList = userService.findAll();
        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        UserDto userDto = userService.save(userSaveRequestDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.update(userDto);
        return updatedUserDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


}
