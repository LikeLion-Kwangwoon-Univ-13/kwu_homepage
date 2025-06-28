package com.example.homepage.PostDir.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDTO {
    private String title;
    private String content;
    private String url;
    private String thumbnail;
    private List<String> tags;
}
