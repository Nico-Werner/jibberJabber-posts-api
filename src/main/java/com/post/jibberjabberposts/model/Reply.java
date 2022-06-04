package com.post.jibberjabberposts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Reply {

    @Id
    private UUID id;

    private String content;

    private UUID authorId;

    //private Long postId;

}
