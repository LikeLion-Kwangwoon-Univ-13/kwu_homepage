package Project.LIKELION.Repository.POST;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.LIKELION.Entity.POST.PostEntity;
import Project.LIKELION.Entity.POST.PostTagEntity;
import Project.LIKELION.Entity.POST.TagEntity;

import java.util.Optional;
import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTagEntity, Integer> {
    Optional<PostTagEntity> findByPostAndTag(PostEntity post, TagEntity tag);
    void deleteByPost(PostEntity post);
    List<PostTagEntity> findByPost(PostEntity post);
    boolean existsByPostAndTag(PostEntity post, TagEntity tag);
    void deleteByPostAndTag(PostEntity post, TagEntity tag);

}
