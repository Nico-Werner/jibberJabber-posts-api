package com.post.jibberjabberposts.service;

import com.post.jibberjabberposts.dto.UserDto;
import com.post.jibberjabberposts.model.User;

import java.util.UUID;

public interface UserService {
    UserDto getLoggedUser();

    UserDto getUser(UUID userId);

    UserDto searchUser(String searchUser);

    User getCurrentUser();

    User getUserById(UUID userId);
}
