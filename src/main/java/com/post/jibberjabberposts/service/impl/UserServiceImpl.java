package com.post.jibberjabberposts.service.impl;

import com.post.jibberjabberposts.dto.UserDto;
import com.post.jibberjabberposts.model.User;
import com.post.jibberjabberposts.repository.UserRepository;
import com.post.jibberjabberposts.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getLoggedUser() {
        User user = getCurrentUser();
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .bio(user.getBio())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User getCurrentUser() {
        KeycloakPrincipal principal = (KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String username = accessToken.getPreferredUsername();

        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .bio(user.getBio())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public UserDto searchUser(String searchUser) {
        User user = userRepository.findAllByUsernameContainingOrDisplayNameContaining(searchUser, searchUser);
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .bio(user.getBio())
                .avatar(user.getAvatar())
                .build();
    }
}
