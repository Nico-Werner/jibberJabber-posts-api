package com.post.jibberjabberposts.dto;

import com.post.jibberjabberposts.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {

    private UUID id;

    private String username;

    private String displayName;

    private String bio;

    private String avatar;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .bio(user.getBio())
                .avatar(user.getAvatar())
                .build();
    }
}
