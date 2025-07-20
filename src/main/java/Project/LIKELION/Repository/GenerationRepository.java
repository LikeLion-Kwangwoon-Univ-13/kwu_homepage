//package Project.LIKELION.Repository.MEMBERS;
//
//import Project.LIKELION.Entity.MEMBERS.GenerationEntity;
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface GenerationRepository extends JpaRepository<GenerationEntity, Integer> { //여기 뒤에 Integer long 이런거 쓰는 이유가 뭐더라
//    @EntityGraph(attributePaths = "id")
//    List<GenerationEntity> findAll();
//
//    @EntityGraph(attributePaths = "id")
//    Optional<GenerationEntity> findById(Integer id);
//}
