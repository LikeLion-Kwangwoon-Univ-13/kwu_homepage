package com.example.homepage.PostDir.tag.service;

import com.example.homepage.PostDir.tag.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getOrCreate(String tagName);
    List<Tag> getTagsByNames(List<String> tagNames);
}
