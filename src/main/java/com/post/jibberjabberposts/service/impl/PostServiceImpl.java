package com.post.jibberjabberposts.service.impl;

import com.post.jibberjabberposts.dto.PostCreationDto;
import com.post.jibberjabberposts.dto.PostDto;
import com.post.jibberjabberposts.dto.ReplyCreationDto;
import com.post.jibberjabberposts.model.Post;
import com.post.jibberjabberposts.model.Reply;
import com.post.jibberjabberposts.model.User;
import com.post.jibberjabberposts.repository.PostRepository;
import com.post.jibberjabberposts.service.PostService;
import com.post.jibberjabberposts.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    private final Logger logger = Logger.getLogger(PostServiceImpl.class.getName());

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public PostDto createPost(PostCreationDto postCreationDto) {
        User user = userService.getCurrentUser();
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Post post = Post.builder()
                .content(postCreationDto.getText())
                .user(user)
                .replies(new ArrayList<>())
                .build();
        logger.info("New Post Started");
        post = postRepository.save(post);
        logger.info("User " + post.getUser().getId() + " has posted: " + post.getId());
        return PostDto.from(post);
    }

    @Override
    public PostDto getPost(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDto.from(post);
    }

    @Override
    public Page<PostDto> getPostsByUser(UUID userId, int page, int size) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        } else {
            Pageable pageable = PageRequest.of(page, size);
            Page<Post> posts = postRepository.findAllByUserId(userId, pageable);
            return posts.map(PostDto::from);
        }
    }

    @Override
    public PostDto createReply(UUID postId, ReplyCreationDto replyCreationDto) {
        User user = userService.getCurrentUser();
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Reply reply = Reply.builder()
                .content(replyCreationDto.getText())
                .user(user)
                .build();
        post.getReplies().add(reply);
        logger.info("New Reply");
        post = postRepository.save(post);
        logger.info("User " + reply.getUser().getId() + " has replied to post " + post.getId());
        return PostDto.from(post);
    }

    @Override
    public void deletePost(UUID postId) {
        logger.info("Deleting Post: " + postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userService.getCurrentUser();
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if(user.getId() != post.getUser().getId()) {
            throw new IllegalArgumentException("User cannot delete post for other user");
        }
        postRepository.delete(post);
        logger.info("Post deleted");
    }

    @Override
    public Page<PostDto> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(PostDto::from);
    }
}
