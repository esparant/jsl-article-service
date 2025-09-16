package com.projects.jslarticle.mapper;

import com.projects.jslarticle.dto.UserDto;
import com.projects.jslarticle.entity.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-16T13:36:47+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.email( user.getEmail() );
        userDto.nickname( user.getNickname() );
        userDto.tag( user.getTag() );
        userDto.point( user.getPoint() );
        userDto.profileImageUrl( user.getProfileImageUrl() );

        return userDto.build();
    }
}
