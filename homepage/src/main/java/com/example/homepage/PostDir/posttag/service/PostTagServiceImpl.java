package com.example.homepage.PostDir.posttag.service;

import com.example.homepage.PostDir.posttag.repository.PostTagRepository;
import com.example.homepage.PostDir.post.entity.Post;
import com.example.homepage.PostDir.posttag.entity.PostTag;
import com.example.homepage.PostDir.tag.entity.Tag;
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
