package com.post.jibberjabberposts.dto;

import com.post.jibberjabberposts.model.Post;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PostDto {

    private UUID id;

    private String text;

    private UserDto user;

    private List<ReplyDto> replies;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .text(post.getContent())
                .user(UserDto.from(post.getUser()))
                .replies(ReplyDto.from(post.getReplies()))
                .build();
    }
}
