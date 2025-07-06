package com.example.homepage.Post.repository;

import com.example.homepage.Post.entity.Post;
import com.example.homepage.Post.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop4ByIsBestOrderByCreatedAtDesc(Boolean isBest);
    List<Post> findTop5ByOrderByCreatedAtDesc();

    @Query("select pt.tag "
            + "from PostTag pt "
            + "where pt.post.id = :postId")
    List<Tag> findTagsByPostId(@Param("postId") Long postId);
}
