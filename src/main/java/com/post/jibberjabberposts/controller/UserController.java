package com.post.jibberjabberposts.controller;

import com.post.jibberjabberposts.dto.UserDto;
import com.post.jibberjabberposts.model.User;
import com.post.jibberjabberposts.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Timed("user_controller_time")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Timed
    public ResponseEntity<?> getLoggedUser() {
        UserDto user = userService.getLoggedUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<?> getUser(@PathVariable("id") UUID userId) {
        UserDto user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search/{searchUser}")
    @Timed
    public ResponseEntity<?> getUser(@PathVariable("searchUser") String searchUser) {
        UserDto users = userService.searchUser(searchUser);
        return ResponseEntity.ok(users);
    }
}
