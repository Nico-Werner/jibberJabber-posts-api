package com.post.jibberjabberposts.controller;

import com.post.jibberjabberposts.dto.PostCreationDto;
import com.post.jibberjabberposts.dto.PostDto;
import com.post.jibberjabberposts.dto.ReplyCreationDto;
import com.post.jibberjabberposts.dto.ReplyDto;
import com.post.jibberjabberposts.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public PostDto createPost(@RequestBody PostCreationDto postCreationDto) {
        return postService.createPost(postCreationDto);
    }

    @GetMapping("/{postId}")
    public PostDto getPost(@Valid @PathVariable("postId") UUID id) {
        return postService.getPost(id);
    }

    @GetMapping("/user/{userId}")
    public List<PostDto> getPostsByUser(@PathVariable("userId") UUID userId,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        return postService.getPostsByUser(userId, page, size);
    }

    @PostMapping("/{postId}/reply")
    public PostDto replyPost(@Valid @PathVariable("postId") UUID postId,
                              @RequestBody ReplyCreationDto replyCreationDto) {
        return postService.createReply(postId, replyCreationDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@Valid @PathVariable("postId") UUID id) {
        postService.deletePost(id);
    }
}
