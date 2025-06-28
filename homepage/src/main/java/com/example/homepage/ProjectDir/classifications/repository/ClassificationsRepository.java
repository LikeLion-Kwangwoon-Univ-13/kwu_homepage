package com.example.homepage.ProjectDir.classifications.repository;

import com.example.homepage.ProjectDir.classifications.entity.Classifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationsRepository extends JpaRepository<Classifications, Long> {
}
