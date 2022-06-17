package com.post.jibberjabberposts.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class ReplyCreationDto {

    @NotNull
    private String text;

    @NotNull
    private UserDto user;

}
