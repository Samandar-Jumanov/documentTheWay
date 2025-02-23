package com.dtw.mapper;


import com.dtw.dtos.UserDto;
import com.dtw.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);


    @Mapping(ignore = true, target = "password")
    UserDto mapToUserDto (User  user);
    User mapToUser ( UserDto userDto);


}
