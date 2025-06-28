package com.example.homepage.MemberDir.generation.repository;

import com.example.homepage.MemberDir.generation.entity.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
    Optional<Generation> findByGeneration(int generation);
}
