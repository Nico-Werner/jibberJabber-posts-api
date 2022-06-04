package com.post.jibberjabberposts.repository;

import com.post.jibberjabberposts.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByAuthorId(UUID userId, Pageable pageable);
}
