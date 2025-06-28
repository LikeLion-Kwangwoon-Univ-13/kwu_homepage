package com.example.homepage.PostDir.post.service;

import com.example.homepage.PostDir.post.dto.PostRequestDTO;
import com.example.homepage.PostDir.post.dto.PostResponseListDTO;

public interface PostService {
    PostResponseListDTO getHome();
    PostResponseListDTO getCursor(int cursor, int limit);
    void createPost(PostRequestDTO postRequestDTO);
    void updatePost(Long id, PostRequestDTO postRequestDTO);
    void deletePost(Long id);
    void setPostBest(Long id);
    void cancelPostBest(Long id);
}
