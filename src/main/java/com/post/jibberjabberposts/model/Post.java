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
    @GeneratedValue
    private UUID id;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;
}
