package com.post.jibberjabberposts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Post {

    @Id
    private UUID id;

    private String content;

    private UUID authorId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;
}
