package com.example.homepage.ProjectDir.projectimage.repository;

import com.example.homepage.ProjectDir.projectimage.entity.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {
}
