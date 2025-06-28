package com.example.homepage.post.service;

import com.example.homepage.post.converter.PostRequestDtoToPostConverter;
import com.example.homepage.post.dto.PostRequestDTO;
import com.example.homepage.post.dto.PostResponseDTO;
import com.example.homepage.post.dto.PostResponseListDTO;
import com.example.homepage.post.entity.Post;
import com.example.homepage.post.repository.PostRepository;
import com.example.homepage.posttag.repository.PostTagRepository;
import com.example.homepage.posttag.service.PostTagService;
import com.example.homepage.tag.entity.Tag;
import com.example.homepage.tag.repository.TagRepository;
import com.example.homepage.tag.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostTagService postTagService;
    private final TagService tagService;
    private final PostRequestDtoToPostConverter postRequestDtoToPostConverter;

    @Override
    public PostResponseListDTO getHome(){
        List<Post> bestPosts = postRepository.findTop4ByIsBestOrderById(1);
        List<Post> notBestPosts = postRepository.findTop5ByIsBestOrderById(0);
        return new PostResponseListDTO(
                toResponseDTO(bestPosts),
                toResponseDTO(notBestPosts)
        );
    }

    @Override
    public PostResponseListDTO getCursor(int cursor, int limit) {
        Pageable pageable = PageRequest.of(cursor, limit, Sort.by("id").descending());
        List<Post> posts = postRepository.findAll(pageable).getContent();

        if (posts.isEmpty()) {
            throw new EntityNotFoundException("게시물이 없습니다.");
        }

        return new PostResponseListDTO(toResponseDTO(posts));
    }

    @Override
    public void createPost(PostRequestDTO postRequestDTO) {
        Post post = postRequestDtoToPostConverter.convert(postRequestDTO);
        postRepository.save(post);

        List<Tag> tags = tagService.getTagsByNames(postRequestDTO.getTags());
        postTagService.linkTagsToPost(post, tags);
    }

    @Override
    public void updatePost(Integer id, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (postRequestDTO.getTitle() != null)
            post.updateTitle(postRequestDTO.getTitle());

        if (postRequestDTO.getContents() != null)
            post.updateContent(postRequestDTO.getContents());

        if (postRequestDTO.getUrl() != null)
            post.updateUrl(postRequestDTO.getUrl());

        if (postRequestDTO.getThumbnail() != null)
            post.updateThumbnail(postRequestDTO.getThumbnail());

        postRepository.save(post);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        postRepository.delete(post);
    }

    @Override
    public void setPostBest(Integer id, String username, String password) {
        if (!"admin".equals(username) || !"admin".equals(password)) {
            throw new IllegalArgumentException("인증 실패");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (post.getIsBest() == 1) {
            throw new IllegalStateException("이미 베스트 게시글입니다.");
        }

        post.updateIsBest(1);
        postRepository.save(post);
    }

    @Override
    public void cancelPostBest(Integer id, String username, String password) {
        if (!"admin".equals(username) || !"admin".equals(password)) {
            throw new IllegalArgumentException("인증 실패");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (post.getIsBest() == 0) {
            throw new IllegalStateException("이미 베스트 게시글이 아닙니다.");
        }

        post.updateIsBest(0);
        postRepository.save(post);
    }

    public List<PostResponseDTO> toResponseDTO(List<Post> posts){
        return posts.stream()
                .map(post -> new PostResponseDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getUrl(),
                        post.getThumbnail(),
                        post.getPostTags().stream()
                                .map(pt -> pt.getTag().getName())
                                .toList()
                ))
                .toList();

    }
}
