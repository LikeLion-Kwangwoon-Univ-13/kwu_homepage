package Project.LIKELION.Repository.POST;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Project.LIKELION.Entity.POST.PostEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findByIsBest(Boolean isBest);

    Optional<PostEntity> findById(Integer id);

    List<PostEntity> findTop4ByIsBestOrderByCreatedAtDesc(boolean isBest);

    List<PostEntity> findTop5ByOrderByCreatedAtDesc();

}
