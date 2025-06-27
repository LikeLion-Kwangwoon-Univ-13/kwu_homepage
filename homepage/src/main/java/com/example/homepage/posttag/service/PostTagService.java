package com.example.homepage.posttag.service;

import com.example.homepage.post.entity.Post;
import com.example.homepage.tag.entity.Tag;

import java.util.List;

public interface PostTagService {
    void linkTagsToPost(Post post, List<Tag> tags);
}
