package com.example.homepage.Post.service;

import com.example.homepage.Post.dto.PostRequestDTO;
import com.example.homepage.Post.dto.PostResponseListDTO;
import com.example.homepage.Post.entity.Tag;

import java.util.List;

public interface PostService {
    PostResponseListDTO getHome();
    PostResponseListDTO getCursor(int cursor, int limit);
    void createPost(PostRequestDTO postRequestDTO);
    void updatePost(Long id, PostRequestDTO postRequestDTO);
    void deletePost(Long id);
    void setPostBest(Long id);
    void cancelPostBest(Long id);
}
