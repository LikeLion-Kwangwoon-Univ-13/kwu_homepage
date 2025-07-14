package Project.LIKELION.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Project.LIKELION.Entity.MEMBERS.StacksEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StacksRepository extends JpaRepository<StacksRepository, Integer> {
        @EntityGraph(attributePaths = "id")
        List<StacksRepository> findAll();

        @EntityGraph(attributePaths = "id")
        Optional<StacksRepository> findById(Integer id);
}
