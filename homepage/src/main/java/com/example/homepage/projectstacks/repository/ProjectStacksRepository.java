package com.example.homepage.projectstacks.repository;

import com.example.homepage.projectstacks.entity.ProjectStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStacksRepository extends JpaRepository<ProjectStacks, Long> {
}
