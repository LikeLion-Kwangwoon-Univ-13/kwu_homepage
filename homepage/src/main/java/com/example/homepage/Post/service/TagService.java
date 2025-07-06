package com.example.homepage.Post.service;

import com.example.homepage.Post.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getOrCreate(String tagName);
    List<Tag> getTagsByNames(List<String> tagNames);
    List<Tag> getOrCreateTagsByNames(List<String> names);
}
