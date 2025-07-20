package Project.LIKELION.Repository.POST;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Project.LIKELION.Entity.POST.TagEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    // 태그 이름으로 조회할 일이 있으면 추가한당
    Optional<TagEntity> findByName(String name);

    //안에 이름이 있으면 반환
    List<TagEntity> findByNameIn(List<String> tagNames);
}

