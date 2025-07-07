package com.example.homepage.Project.repository;

import com.example.homepage.Project.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
    List<ProjectImage> findAllByProjectIdOrderByOrderIndexAsc(Long projectId);
    void deleteAllByProjectId(Long projectId);
}
