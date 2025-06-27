package com.example.homepage.tag.service;

import com.example.homepage.tag.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getOrCreate(String tagName);
    List<Tag> getTagsByNames(List<String> tagNames);
}
