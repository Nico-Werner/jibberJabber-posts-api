package com.post.jibberjabberposts.dto;

import com.post.jibberjabberposts.model.Reply;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ReplyDto {

    private UUID id;

    private String content;

    private UUID authorId;

    public static List<ReplyDto> from(List<Reply> replies) {
        List<ReplyDto> replyDto = new ArrayList<>();
        replies.forEach(reply -> replyDto.add(ReplyDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .authorId(reply.getAuthorId())
                .build()));
        return replyDto;
    }
}
