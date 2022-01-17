package com.aysegulapc.fourthhomework.usr.service;

import com.aysegulapc.fourthhomework.usr.converter.UserMapper;
import com.aysegulapc.fourthhomework.usr.dto.UserDto;
import com.aysegulapc.fourthhomework.usr.dto.UserSaveRequestDto;
import com.aysegulapc.fourthhomework.usr.entity.User;
import com.aysegulapc.fourthhomework.usr.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityService userEntityService;

    public List<UserDto> findAll() {
        List<User> userList = userEntityService.findAll();
        List<UserDto> userDtoList = UserMapper.INSTANCE.convertAllUserDtoToUser(userList);
        return userDtoList;
    }

    public UserDto save(UserSaveRequestDto userSaveRequestDto) {
        User user = UserMapper.INSTANCE.convertUserSaveDtoToUser(userSaveRequestDto);
        user = userEntityService.save(user);
        UserDto userDto = UserMapper.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    public UserDto update(UserDto userDto) {
        User user = UserMapper.INSTANCE.convertUserDtoToUser(userDto);
        userEntityService.save(user);
        userDto = UserMapper.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    public void delete(Long id) {
        User user = findUserById(id);
        userEntityService.delete(user);
    }

    private User findUserById(Long id) {
        Optional<User> optionalUser = userEntityService.findById(id);

        User user;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new RuntimeException("User not found!");
        }

        return user;
    }

}
