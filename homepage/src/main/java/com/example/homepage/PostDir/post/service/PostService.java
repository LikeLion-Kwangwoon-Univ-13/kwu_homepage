package com.example.homepage.PostDir.post.service;

import com.example.homepage.PostDir.post.dto.PostRequestDTO;
import com.example.homepage.PostDir.post.dto.PostResponseListDTO;

public interface PostService {
    PostResponseListDTO getHome();
    PostResponseListDTO getCursor(int cursor, int limit);
    void createPost(PostRequestDTO postRequestDTO);
    void updatePost(Integer id, PostRequestDTO postRequestDTO);
    void deletePost(Integer id);
    void setPostBest(Integer id, String username, String password);
    void cancelPostBest(Integer id, String username, String password);
}
