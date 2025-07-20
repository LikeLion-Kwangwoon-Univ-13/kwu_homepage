//package Project.LIKELION.Repository.MEMBERS;
//
//import Project.LIKELION.Entity.MEMBERS.MembersEntity;
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface MembersRepository extends JpaRepository<MembersEntity, Integer>  {
//    @EntityGraph(attributePaths = "id")
//    List<MembersEntity> findAll();
//
//    @EntityGraph(attributePaths = "id")
//    Optional<MembersEntity> findById(Integer id);
//}
