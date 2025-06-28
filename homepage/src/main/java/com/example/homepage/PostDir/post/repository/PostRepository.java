package com.example.homepage.PostDir.post.repository;

import com.example.homepage.PostDir.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop4ByIsBestOrderById(Boolean isBest);
    List<Post> findTop5ByIsBestOrderById(Boolean isBest);
}
