package com.example.homepage.PostDir.tag.service;

import com.example.homepage.PostDir.tag.repository.TagRepository;
import com.example.homepage.PostDir.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public Tag getOrCreate(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(new Tag(tagName)));
    }

    @Override
    public List<Tag> getTagsByNames(List<String> tagNames) {
        return tagNames.stream()
                .map(this::getOrCreate)
                .toList();
    }
}
