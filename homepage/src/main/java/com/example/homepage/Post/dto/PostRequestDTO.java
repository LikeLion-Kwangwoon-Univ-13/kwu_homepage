package com.example.homepage.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostRequestDTO {
    private String title;
    private String content;
    private String url;
    private String thumbnail;
    private List<String> tags;
}
