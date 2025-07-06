package com.example.homepage.Post.service;

import com.example.homepage.Post.converter.PostRequestDtoToPostConverter;
import com.example.homepage.Post.dto.PostRequestDTO;
import com.example.homepage.Post.repository.PostRepository;
import com.example.homepage.Post.dto.PostResponseDTO;
import com.example.homepage.Post.dto.PostResponseListDTO;
import com.example.homepage.Post.entity.Post;
import com.example.homepage.Post.entity.Tag;
import com.example.homepage.Post.repository.PostTagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final PostTagService postTagService;
    private final TagService tagService;
    private final PostRequestDtoToPostConverter postRequestDtoToPostConverter;

    @Override
    public PostResponseListDTO getHome(){
        List<Post> bestPosts = postRepository.findTop4ByIsBestOrderByCreatedAtDesc(true);
        List<Post> notBestPosts = postRepository.findTop5ByOrderByCreatedAtDesc();
        return new PostResponseListDTO(
                toResponseDTO(bestPosts),
                toResponseDTO(notBestPosts)
        );
    }

    @Override
    public PostResponseListDTO getCursor(int cursor, int limit) {
        Pageable pageable = PageRequest.of(cursor, limit, Sort.by("createdAt").descending());
        List<Post> posts = postRepository.findAll(pageable).getContent();

        return new PostResponseListDTO(toResponseDTO(posts));
    }

    @Override
    public void createPost(PostRequestDTO postRequestDTO) {
        Post post = postRequestDtoToPostConverter.convert(postRequestDTO);
        postRepository.save(post);

        // Tag 이름 목록을 받아서, DB에 없으면 새로 저장하고 모두 반환
        List<Tag> tags = tagService.getOrCreateTagsByNames(postRequestDTO.getTags());

        // PostTag 조인 레코드 생성
        postTagService.linkTagsToPost(post, tags);
    }

    @Override
    public void updatePost(Long id, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        // 기본 업데이트
        if (postRequestDTO.getTitle() != null)
            post.updateTitle(postRequestDTO.getTitle());
        if (postRequestDTO.getContent() != null)
            post.updateContent(postRequestDTO.getContent());
        if (postRequestDTO.getUrl() != null)
            post.updateUrl(postRequestDTO.getUrl());
        if (postRequestDTO.getThumbnail() != null)
            post.updateThumbnail(postRequestDTO.getThumbnail());

        // DTO 넘어온 Tag 목록 준비
        List<Tag> newTags = tagService.getOrCreateTagsByNames(postRequestDTO.getTags());
        List<Tag> existingTags = postRepository.findTagsByPostId(post.getId());

        // 삭제할 태그: 기존에는 있는데, 새 요청엔 없는 것
        existingTags.stream()
                .filter(tag -> !newTags.contains(tag))
                .forEach(tag -> postTagService.unlinkTagFromPost(post, tag));

        // 6) 추가할 태그: 요청엔 있는데, 기존엔 없는 것
        newTags.stream()
                .filter(tag -> !existingTags.contains(tag))
                .forEach(tag -> postTagService.linkTagToPost(post, tag));

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        // post 연관된 postTag 삭제
        postTagRepository.deleteByPost(post);

        // post 삭제
        postRepository.delete(post);
    }

    @Override
    public void setPostBest(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (post.getIsBest()) {
            throw new IllegalStateException("이미 베스트 게시글입니다.");
        }

        //postTagRepository.deleteByPost(post);
        post.updateIsBest(true);
        postRepository.save(post);
    }

    @Override
    public void cancelPostBest(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (!post.getIsBest()) {
            throw new IllegalStateException("이미 베스트 게시글이 아닙니다.");
        }

        post.updateIsBest(false);
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
                        // Tag → String 변환
                        postRepository.findTagsByPostId(post.getId()).stream()
                                .map(Tag::getName)
                                .collect(Collectors.toList())
                ))
                .toList();
    }
}
