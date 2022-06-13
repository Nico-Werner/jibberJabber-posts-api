package com.post.jibberjabberposts.dto;

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
}
