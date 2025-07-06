package com.example.homepage.Project.repository;

import com.example.homepage.Project.entity.ProjectStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStacksRepository extends JpaRepository<ProjectStacks, Long> {
}
