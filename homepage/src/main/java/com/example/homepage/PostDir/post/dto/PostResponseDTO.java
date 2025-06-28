package com.example.homepage.PostDir.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
