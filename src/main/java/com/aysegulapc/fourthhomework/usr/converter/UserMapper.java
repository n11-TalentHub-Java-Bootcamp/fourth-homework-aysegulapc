package com.aysegulapc.fourthhomework.usr.converter;

import com.aysegulapc.fourthhomework.usr.dto.UserDto;
import com.aysegulapc.fourthhomework.usr.dto.UserSaveRequestDto;
import com.aysegulapc.fourthhomework.usr.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> convertAllUserDtoToUser(List<User> userList);

    User convertUserSaveDtoToUser(UserSaveRequestDto userSaveRequestDtoDto);

    User convertUserDtoToUser(UserDto userDto);

    UserDto convertUserToUserDto(User user);
}
