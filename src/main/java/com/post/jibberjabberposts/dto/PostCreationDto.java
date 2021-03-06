package com.post.jibberjabberposts.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PostCreationDto {

    @NotNull
    private String text;

    @NotNull
    private UserDto user;

}
