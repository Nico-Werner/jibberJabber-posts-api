package com.post.jibberjabberposts.repository;

import com.post.jibberjabberposts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findById(UUID userId);

    User findAllByUsernameContainingOrDisplayNameContaining(String username, String displayName);
}
