package com.post.jibberjabberposts.repository;

import com.post.jibberjabberposts.dto.PostDto;
import com.post.jibberjabberposts.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Page<Post> findAllByUserId(UUID userId, Pageable pageable);
}
