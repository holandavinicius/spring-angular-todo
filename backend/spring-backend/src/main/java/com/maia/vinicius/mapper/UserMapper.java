package com.maia.vinicius.mapper;

import com.maia.vinicius.dto.UserRequest;
import com.maia.vinicius.dto.UserResponse;
import com.maia.vinicius.model.User;

public class UserMapper {
    public static User toEntity(UserRequest dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponse toDto(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail()
        );
    }
}
