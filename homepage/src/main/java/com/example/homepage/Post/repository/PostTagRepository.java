package com.example.homepage.Post.repository;

import com.example.homepage.Post.entity.Post;
import com.example.homepage.Post.entity.PostTag;
import com.example.homepage.Post.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    Optional<PostTag> findByPostAndTag(Post post, Tag tag);
    void deleteByPost(Post post);
}
