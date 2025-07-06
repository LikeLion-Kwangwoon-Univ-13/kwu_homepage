package com.example.homepage.Post.service;

import com.example.homepage.Post.entity.Post;
import com.example.homepage.Post.entity.Tag;

import java.util.List;

public interface PostTagService {
    void linkTagsToPost(Post post, List<Tag> tags);

    void linkTagToPost(Post post, Tag tag);
    void unlinkTagFromPost(Post post, Tag tag);
}
