package com.example.homepage.PostDir.posttag.service;

import com.example.homepage.PostDir.post.entity.Post;
import com.example.homepage.PostDir.tag.entity.Tag;

import java.util.List;

public interface PostTagService {
    void linkTagsToPost(Post post, List<Tag> tags);
}
