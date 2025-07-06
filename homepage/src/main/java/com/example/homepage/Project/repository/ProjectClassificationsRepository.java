package com.example.homepage.Project.repository;

import com.example.homepage.Project.entity.ProjectClassifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectClassificationsRepository extends JpaRepository<ProjectClassifications, Long> {
}
