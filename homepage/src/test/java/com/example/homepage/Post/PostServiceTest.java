package com.example.homepage.Post;

import com.example.homepage.Post.converter.PostRequestDtoToPostConverter;
import com.example.homepage.Post.dto.PostRequestDTO;
import com.example.homepage.Post.entity.Post;
import com.example.homepage.Post.entity.Tag;
import com.example.homepage.Post.repository.PostRepository;
import com.example.homepage.Post.repository.PostTagRepository;
import com.example.homepage.Post.service.PostServiceImpl;
import com.example.homepage.Post.service.PostTagService;
import com.example.homepage.Post.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private PostTagRepository postTagRepository;
    @Mock
    private PostTagService postTagService;
    @Mock
    private TagService tagService;
    @Mock
    private PostRequestDtoToPostConverter converter;

    @InjectMocks
    private
    PostServiceImpl service;

    private Post samplePost;
    private Tag sampleTag;

    @BeforeEach
    void setUp() {
        samplePost = Post.builder()
                .title("title")
                .content("content")
                .url("url")
                .thumbnail("thumb")
                .build();
        ReflectionTestUtils.setField(samplePost, "id", 1L);

        sampleTag = Tag.builder().name("tag1").build();
        ReflectionTestUtils.setField(sampleTag, "id", 10L);
    }

    @Test
    void whenCreatePost_thenSavesAndLinksTags() {
        // given
        PostRequestDTO dto = new PostRequestDTO();
        dto.setTitle("t"); dto.setContent("c"); dto.setUrl("u"); dto.setThumbnail("thumb");
        dto.setTags(List.of("tag1"));
        given(converter.convert(dto)).willReturn(samplePost);
        given(tagService.getOrCreateTagsByNames(dto.getTags())).willReturn(List.of(sampleTag));

        // when
        service.createPost(dto);

        // then
        then(postRepository).should().save(samplePost);
        then(postTagService).should().linkTagsToPost(samplePost, List.of(sampleTag));
    }

    @Test
    void whenDeleteNonexistentPost_thenThrows() {
        // given
        given(postRepository.findById(1L)).willReturn(Optional.empty());

        // expect
        assertThatThrownBy(() -> service.deletePost(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("게시글을 찾을 수 없습니다");
    }

    @Test
    void whenDeletePost_thenDeletesTagsAndPost() {
        // given
        given(postRepository.findById(1L)).willReturn(Optional.of(samplePost));

        // when
        service.deletePost(1L);

        // then
        then(postTagRepository).should().deleteByPost(samplePost);
        then(postRepository).should().delete(samplePost);
    }

    @Test
    void whenCursor_thenPagingWorks() {
        // given
        Page<Post> page = new PageImpl<>(List.of(samplePost));
        given(postRepository.findAll(any(Pageable.class))).willReturn(page);
        given(postRepository.findTagsByPostId(any(Long.class))).willReturn(Collections.emptyList());

        // when
        var dto = service.getCursor(0, 5);

        // then
        assertThat(dto.getPosts()).hasSize(1);
    }
}
