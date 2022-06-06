package com.post.jibberjabberposts.service;

import com.post.jibberjabberposts.dto.PostCreationDto;
import com.post.jibberjabberposts.dto.PostDto;
import com.post.jibberjabberposts.dto.ReplyCreationDto;
import com.post.jibberjabberposts.dto.ReplyDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostDto createPost(PostCreationDto postCreationDto);

    PostDto getPost(UUID postId);

    Page<PostDto> getPostsByUser(UUID userId, int page, int size);

    PostDto createReply(UUID postId, ReplyCreationDto replyCreationDto);

    void deletePost(UUID postId);

    Page<PostDto> getAllPosts(int page, int size);
}
