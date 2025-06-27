package com.example.homepage.posttag.service;

import com.example.homepage.post.entity.Post;
import com.example.homepage.posttag.entity.PostTag;
import com.example.homepage.posttag.repository.PostTagRepository;
import com.example.homepage.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService {
    private final PostTagRepository postTagRepository;

    @Override
    public void linkTagsToPost(Post post, List<Tag> tags) {
        for (Tag tag : tags) {
            boolean alreadyLinked = post.getPostTags().stream()
                    .anyMatch(pt -> pt.getTag().getId().equals(tag.getId()));
            if (!alreadyLinked) {
                PostTag postTag = new PostTag(post, tag);
                postTagRepository.save(postTag);
                post.getPostTags().add(postTag);
            }
        }

    }
}
