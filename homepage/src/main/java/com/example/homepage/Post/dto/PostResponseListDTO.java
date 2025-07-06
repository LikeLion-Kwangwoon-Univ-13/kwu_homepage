package com.example.homepage.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseListDTO {
    private List<PostResponseDTO> best;
    private List<PostResponseDTO> posts;

    public PostResponseListDTO(List<PostResponseDTO> posts) {
        this.posts = posts;
    }
}
