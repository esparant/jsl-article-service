package com.projects.jslarticle.mapper;


import com.projects.jslarticle.dto.UserDto;
import com.projects.jslarticle.entity.user.User;
import org.mapstruct.Mapper;

/**
 * @author 탁영복
 * @version 1.0.0
 * @description MapStruct - UserMapper 인터페이스 파일입니다.
 * @since 2025-09-15
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}
