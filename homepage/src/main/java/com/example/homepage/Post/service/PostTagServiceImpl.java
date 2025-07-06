package com.example.homepage.Post.service;

import com.example.homepage.Post.repository.PostRepository;
import com.example.homepage.Post.repository.PostTagRepository;
import com.example.homepage.Post.entity.Post;
import com.example.homepage.Post.entity.PostTag;
import com.example.homepage.Post.entity.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService {
    private final PostTagRepository postTagRepository;
    private final PostRepository postRepository;

    @Override
    public void linkTagsToPost(Post post, List<Tag> tags) {
        // 연결된 태그 조회
        List<Tag> existingTags = postRepository.findTagsByPostId(post.getId());
        Set<Long> existingTagIds = existingTags.stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());

        // 새로 넘어온 태그 목록을 조회, 없는 것만 연결
        for (Tag tag : tags) {
            if (!existingTagIds.contains(tag.getId())) {
                PostTag postTag = new PostTag(post, tag);
                postTagRepository.save(postTag);
            }
        }
    }

    @Override
    public void linkTagToPost(Post post, Tag tag) {
        PostTag pt = PostTag.builder()
                .post(post)
                .tag(tag)
                .build();
        postTagRepository.save(pt);
    }

    @Override
    public void unlinkTagFromPost(Post post, Tag tag) {
        PostTag pt = postTagRepository.findByPostAndTag(post, tag)
                .orElseThrow(() -> new EntityNotFoundException("PostTag 관계를 찾을 수 없습니다."));
        postTagRepository.delete(pt);
    }
}
