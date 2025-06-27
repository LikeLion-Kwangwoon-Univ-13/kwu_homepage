package com.example.homepage.post.service;

import com.example.homepage.post.dto.PostRequestDTO;
import com.example.homepage.post.dto.PostResponseListDTO;

public interface PostService {
    PostResponseListDTO getHome();
    PostResponseListDTO getCursor(int cursor, int limit);
    void createPost(PostRequestDTO postRequestDTO);
    void updatePost(Integer id, PostRequestDTO postRequestDTO);
    void deletePost(Integer id);
}
