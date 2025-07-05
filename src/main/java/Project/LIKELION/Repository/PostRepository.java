package Project.LIKELION.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Project.LIKELION.Entity.POST.PostEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    @EntityGraph(attributePaths = "tags")
    List<PostEntity> findAll();   // 모든 Post + Tag들을 한 번의 쿼리로 가져옴

    @EntityGraph(attributePaths = "tags")
    Optional<PostEntity> findById(Integer id);

}
