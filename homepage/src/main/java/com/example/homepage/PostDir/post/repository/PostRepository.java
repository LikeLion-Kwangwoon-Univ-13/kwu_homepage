package com.example.homepage.PostDir.post.repository;

import com.example.homepage.PostDir.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findTop4ByIsBestOrderById(Integer isBest);
    List<Post> findTop5ByIsBestOrderById(Integer isBest);
}
