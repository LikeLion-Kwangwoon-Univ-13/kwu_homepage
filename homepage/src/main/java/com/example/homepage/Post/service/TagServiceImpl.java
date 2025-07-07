package com.example.homepage.Post.service;

import com.example.homepage.Post.repository.TagRepository;
import com.example.homepage.Post.entity.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
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

    @Override
    public List<Tag> getOrCreateTagsByNames(List<String> names) {
        List<Tag> result = new ArrayList<>();

        for (String name : names) {
            Tag tag = tagRepository.findByName(name)
                    .orElseGet(() -> tagRepository.save(
                            Tag.builder()
                                    .name(name)
                                    .build()
                    ));
            result.add(tag);
        }

        return result;
    }
}
