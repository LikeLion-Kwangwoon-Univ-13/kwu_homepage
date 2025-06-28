package com.example.homepage.PostDir.tag.service;

import com.example.homepage.PostDir.tag.repository.TagRepository;
import com.example.homepage.PostDir.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        // 빈 리스트 입력 처리
        if (tagNames == null || tagNames.isEmpty()) {
            return Collections.emptyList();
        }

        // 중복 제거 추가
        return tagNames.stream()
                .distinct()
                .map(this::getOrCreate)
                .toList();
    }
}
