package Project.LIKELION.Service.POST;

import Project.LIKELION.Entity.POST.PostEntity;
import Project.LIKELION.Entity.POST.PostTagEntity;
import Project.LIKELION.Entity.POST.TagEntity;
import Project.LIKELION.Repository.POST.PostTagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService {

    private final PostTagRepository postTagRepository;

    // 여러 태그를 게시글에 연결
    @Override
    public void linkTagsToPost(PostEntity post, List<TagEntity> tags) {
        List<PostTagEntity> existingPostTags = postTagRepository.findByPost(post);
        Set<Integer> existingTagIds = existingPostTags.stream()
                .map(pt -> pt.getTag().getId())
                .collect(Collectors.toSet());

        for (TagEntity tag : tags) {
            if (!existingTagIds.contains(tag.getId())) {
                PostTagEntity postTag = PostTagEntity.builder()
                        .post(post)
                        .tag(tag)
                        .build();
                postTagRepository.save(postTag);
            }
        }
    }

    // 단일 태그 연결
    @Override
    public void linkTagToPost(PostEntity post, TagEntity tag) {
        boolean exists = postTagRepository.existsByPostAndTag(post, tag);
        if (!exists) {
            PostTagEntity postTag = PostTagEntity.builder()
                    .post(post)
                    .tag(tag)
                    .build();
            postTagRepository.save(postTag);
        }
    }

    // 태그 연결 해제
    @Override
    public void unlinkTagFromPost(PostEntity post, TagEntity tag) {
        postTagRepository.deleteByPostAndTag(post, tag);
    }
}
