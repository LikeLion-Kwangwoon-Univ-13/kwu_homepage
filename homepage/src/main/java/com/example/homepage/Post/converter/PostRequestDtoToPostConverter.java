package com.example.homepage.Post.converter;

import com.example.homepage.Post.dto.PostRequestDTO;
import com.example.homepage.Post.entity.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostRequestDtoToPostConverter implements Converter<PostRequestDTO, Post> {

    @Override
    public Post convert(PostRequestDTO source) {
        return Post.builder()
                .title(source.getTitle())
                .content(source.getContent())
                .url(source.getUrl())
                .thumbnail(source.getThumbnail())
                .build();
    }
}
