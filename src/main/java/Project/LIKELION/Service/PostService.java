package Project.LIKELION.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.LIKELION.Repository.PostRepository;
import Project.LIKELION.Repository.TagRepository;
import Project.LIKELION.DTO.POST.PostDTO;
import Project.LIKELION.DTO.POST.TagDTO;
import Project.LIKELION.Entity.POST.PostEntity;
import Project.LIKELION.Entity.POST.TagEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    private PostDTO mapToDto(PostEntity e) {
        List<TagDTO> tagDtos = e.getTags().stream()
                .map(t -> TagDTO.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .build())
                .collect(Collectors.toList());

        return PostDTO.builder()
                .id(e.getId())
                .isBest(e.getIsBest())
                .title(e.getTitle())
                .content(e.getContent())
                .url(e.getUrl())
                .thumbnail(e.getThumbnail())
                .isDeleted(e.getIsDeleted())
                .tags(tagDtos)
                .build();
    }

    @Transactional(readOnly = true)
    public PostDTO getPostById(Integer id) {
        return postRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Post Not Found: " + id));
    }

    @Transactional
    public PostDTO createPost(PostDTO dto) {
        PostEntity p = PostEntity.builder()
                .isBest(dto.getIsBest())
                .title(dto.getTitle())
                .content(dto.getContent())
                .url(dto.getUrl())
                .thumbnail(dto.getThumbnail())
                .isDeleted(dto.getIsDeleted())
                .build();

        // 태그 처리 기능. id가 있으면 로드, 없으면 생성
        List<TagEntity> tags = dto.getTags().stream()
                .map(tagDto -> {
                    if (tagDto.getId() != null) {
                        return tagRepository.findById(tagDto.getId())
                                .orElseThrow(() -> new RuntimeException("Tag Not Found: " + tagDto.getId()));
                    } else {
                        TagEntity newTag = TagEntity.builder()
                                .name(tagDto.getName())
                                .build();
                        return tagRepository.save(newTag);
                    }
                })
                .collect(Collectors.toList());

        // 직접 컬렉션에 추가
        p.getTags().addAll(tags);

        PostEntity saved = postRepository.save(p);
        return mapToDto(saved);
    }

    @Transactional
    public PostDTO updatePost(PostDTO dto) {
        PostEntity p = postRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Post Not Found: " + dto.getId()));

        // 태그 업데이트: 기존 모두 삭제 후 재등록하도록 함.
        p.getTags().clear();
        List<TagEntity> tags = dto.getTags().stream()
                .map(tagDto -> {
                    if (tagDto.getId() != null) {
                        return tagRepository.findById(tagDto.getId())
                                .orElseThrow(() -> new RuntimeException("Tag Not Found: " + tagDto.getId()));
                    } else {
                        TagEntity newTag = TagEntity.builder()
                                .name(tagDto.getName())
                                .build();
                        return tagRepository.save(newTag);
                    }
                })
                .collect(Collectors.toList());
        p.getTags().addAll(tags);

        PostEntity updated = postRepository.save(p);
        return mapToDto(updated);
    }

    @Transactional
    public PostDTO deletePost(Integer id) {
        PostEntity p = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post Not Found: " + id));

        PostEntity deleted = postRepository.save(p);
        return mapToDto(deleted);
    }
}
