package Project.LIKELION.Repository;

import Project.LIKELION.Entity.RecruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitRepostory extends JpaRepository<RecruitEntity, Integer> {

}
