package com.example.homepage.classifications.repository;

import com.example.homepage.classifications.entity.Classifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationsRepository extends JpaRepository<Classifications, Long> {
}
