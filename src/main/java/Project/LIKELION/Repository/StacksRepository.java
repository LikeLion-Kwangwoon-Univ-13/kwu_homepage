package Project.LIKELION.Repository.MEMBERS;

import Project.LIKELION.Entity.MEMBERS.StacksEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StacksRepository extends JpaRepository<StacksEntity, Integer> {
        @EntityGraph(attributePaths = "id")
        List<StacksEntity> findAll();

        @EntityGraph(attributePaths = "id")
        Optional<StacksEntity> findById(Integer id);
}
