package com.example.homepage.Project.repository;

import com.example.homepage.Project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findTop5ByOrderByCreatedAtDesc();
    Page<Project> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
