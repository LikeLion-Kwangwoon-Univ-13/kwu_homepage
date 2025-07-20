package Project.LIKELION.Service.POST;

import Project.LIKELION.Converter.PostConverter;
import Project.LIKELION.Entity.POST.PostTagEntity;
import Project.LIKELION.Entity.POST.TagEntity;
import Project.LIKELION.Repository.POST.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.LIKELION.Repository.POST.PostRepository;
import Project.LIKELION.DTO.POST.PostRequestDTO;
import Project.LIKELION.DTO.POST.PostListDTO;
import Project.LIKELION.DTO.POST.PostResponseDTO;
import Project.LIKELION.Entity.POST.PostEntity;
import org.springframework.web.server.ResponseStatusException;
import Project.LIKELION.Repository.POST.PostTagRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;


    // 전체 포스트 목록 조회
    @Override
    @Transactional(readOnly = true)
    public PostListDTO getAllPosts(int cursor, int limit) {
        List<PostEntity> allPosts = postRepository.findAll();

        // 페이지네이션 처리
        List<PostEntity> slicedPosts = allPosts.stream()
                .skip(cursor)
                .limit(limit)
                .toList();

        // 직접 PostResponseDTO 생성
        List<PostResponseDTO> postDTOs = slicedPosts.stream()
                .map(post -> PostResponseDTO.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .url(post.getUrl())
                        .thumbnail(post.getThumbnail())
                        .tags( // tag 이름 리스트로 추출
                                postTagRepository.findByPost(post).stream()
                                        .map(pt -> pt.getTag().getName())
                                        .collect(Collectors.toList())
                        )
                        .build()
                )
                .collect(Collectors.toList());

        return PostListDTO.builder()
                .posts(postDTOs)
                .best(null)
                .build();
    }


    // ID로 특정 포스트 단일 조회
    @Override
    @Transactional(readOnly = true)
    public PostRequestDTO getPostById(Integer id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        List<String> tags = postTagRepository.findByPost(post).stream()
                .map(pt -> pt.getTag().getName())
                .collect(Collectors.toList());

        return PostRequestDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .url(post.getUrl())
                .thumbnail(post.getThumbnail())
                .tags(tags)
                .build();
    }


    // 새로운 포스트 생성
    @Override
    @Transactional
    public void createPost(PostRequestDTO dto) {
        PostEntity post = postConverter.convert(dto);
        postRepository.save(post); // 먼저 저장 (PK 필요하니까)

        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            for (String name : dto.getTags()) {
                TagEntity tag = tagRepository.findByName(name)
                        .orElseGet(() -> tagRepository.save(TagEntity.builder().name(name).build()));

                PostTagEntity postTag = PostTagEntity.builder()
                        .post(post)
                        .tag(tag)
                        .build();

                postTagRepository.save(postTag);
            }
        }
    }


    @Override
    @Transactional
    public void updatePost(PostRequestDTO dto, Integer id) {
        PostEntity existing = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 게시글이 존재하지 않습니다."));

        // 프론트에서 보낸 값만 업데이트
        if (dto.getTitle() != null) existing.updateTitle(dto.getTitle());
        if (dto.getContent() != null) existing.updateContent(dto.getContent());
        if (dto.getUrl() != null) existing.updateUrl(dto.getUrl());
        if (dto.getThumbnail() != null) existing.updateThumbnail(dto.getThumbnail());

        // 태그가 전달된 경우 기존 태그 제거 후 새로운 태그 등록
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            postTagRepository.deleteByPost(existing);

            for (String name : dto.getTags()) {
                TagEntity tag = tagRepository.findByName(name)
                        .orElseGet(() -> tagRepository.save(TagEntity.builder().name(name).build()));

                PostTagEntity postTag = PostTagEntity.builder()
                        .post(existing)
                        .tag(tag)
                        .build();

                postTagRepository.save(postTag);
            }
        }

        postRepository.save(existing);
    }


    // 포스트 삭제
    @Override
    @Transactional
    public void deletePost(Integer id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 게시글이 존재하지 않습니다."));
        postRepository.delete(post);
    }

    // 포스트를 우수 포스트로 지정
    @Override
    @Transactional
    public void markPostAsBest(Integer id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 존재하지 않습니다."));

        post.updateIsBest(true); // isBest 플래그 1로 설정
        postRepository.save(post);
    }

    // 새로운 우수 포스트 생성
    @Override
    @Transactional
    public void createBestPost(Integer id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow();
        post.updateIsBest(true); // 우수 포스트로 설정
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getBestPosts() {
        List<PostEntity> bestPostEntities = postRepository.findTop4ByIsBestOrderByCreatedAtDesc(true);
        return bestPostEntities.stream()
                .map(post -> {
                    List<PostTagEntity> postTags = postTagRepository.findByPost(post);
                    return PostResponseDTO.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .url(post.getUrl())
                            .thumbnail(post.getThumbnail())
                            .tags(
                                    postTags.stream()
                                            .map(pt -> pt.getTag().getName())
                                            .collect(Collectors.toList())
                            )
                            .build();
                })
                .collect(Collectors.toList());
    }


    // 우수 포스트 수정 및 베스트 해제
    @Override
    @Transactional
    public void updateBestPost(Integer id) {
        PostEntity existing = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 게시글이 존재하지 않습니다."));

       existing.updateIsBest(false);
    }

    // 우수 포스트 삭제 (베스트 플래그가 1인 경우만)
    @Override
    @Transactional
    public void deleteBestPost(Integer id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 게시글이 존재하지 않습니다."));

        if (post.isBest()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "베스트 게시글이 아닙니다.");
        }

        postRepository.delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getCursor(int cursor, int limit) {
        // 예시 구현 - 실제 cursor 방식은 네 요구에 따라 다를 수 있음
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream()
                .skip(cursor)
                .limit(limit)
                .map(post -> {
                    // 💡 PostEntity에 postTags가 없으므로, PostTagRepository로 직접 조회
                    List<PostTagEntity> postTags = postTagRepository.findByPost(post);
                    return PostResponseDTO.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .url(post.getUrl())
                            .thumbnail(post.getThumbnail())
                            .tags(
                                    postTags.stream()
                                            .map(postTag -> postTag.getTag().getName())
                                            .collect(Collectors.toList())
                            )
                            .build();
                })
                .collect(Collectors.toList());
    }

}
