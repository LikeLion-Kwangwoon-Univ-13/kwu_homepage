package com.example.homepage.projectclassifications.repository;

import com.example.homepage.projectclassifications.entity.ProjectClassifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectClassificationsRepository extends JpaRepository<ProjectClassifications, Long> {
}
