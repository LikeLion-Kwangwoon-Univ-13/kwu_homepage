package com.example.homepage.posttag.repository;

import com.example.homepage.posttag.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
}
