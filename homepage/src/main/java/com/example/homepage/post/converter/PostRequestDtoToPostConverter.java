package com.example.homepage.post.converter;

import com.example.homepage.post.dto.PostRequestDTO;
import com.example.homepage.post.entity.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostRequestDtoToPostConverter implements Converter<PostRequestDTO, Post> {

    @Override
    public Post convert(PostRequestDTO source) {
        return Post.builder()
                .title(source.getTitle())
                .content(source.getContents())
                .url(source.getUrl())
                .thumbnail(source.getThumbnail())
                .build();
    }
}
