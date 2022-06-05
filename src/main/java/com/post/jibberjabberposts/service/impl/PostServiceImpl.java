package com.post.jibberjabberposts.service.impl;

import com.post.jibberjabberposts.dto.PostCreationDto;
import com.post.jibberjabberposts.dto.PostDto;
import com.post.jibberjabberposts.dto.ReplyCreationDto;
import com.post.jibberjabberposts.model.Post;
import com.post.jibberjabberposts.model.Reply;
import com.post.jibberjabberposts.repository.PostRepository;
import com.post.jibberjabberposts.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostCreationDto postCreationDto) {
        Post post = Post.builder()
                .content(postCreationDto.getContent())
                .authorId(postCreationDto.getAuthorId())
                .replies(new ArrayList<>())
                .build();
        post = postRepository.save(post);
        return PostDto.from(post);
    }

    @Override
    public PostDto getPost(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDto.from(post);
    }

    @Override
    public Page<PostDto> getPostsByUser(UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllByAuthorId(userId, pageable);
        return posts.map(PostDto::from);
    }

    @Override
    public PostDto createReply(UUID postId, ReplyCreationDto replyCreationDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Reply reply = Reply.builder()
                .content(replyCreationDto.getContent())
                .authorId(replyCreationDto.getAuthorId())
                .build();
        post.getReplies().add(reply);
        post = postRepository.save(post);
        return PostDto.from(post);
    }

    @Override
    public void deletePost(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }
}
