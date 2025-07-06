package com.example.homepage.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String contents;
    private String url;
    private String thumbnail;
    private List<String> tags;
}
