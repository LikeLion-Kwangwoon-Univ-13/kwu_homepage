package com.example.homepage.Project.repository;

import com.example.homepage.Project.entity.Projects;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    List<Projects> findTop5ByOrderById();
    List<Projects> findByIdGreaterThanOrderByIdAsc(Integer cursor, Pageable pageable);
}
