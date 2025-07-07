package com.example.homepage.Member.repository;

import com.example.homepage.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByGenerationId(Long generationId);
    List<Member> findByGeneration_Generation(int generation);
    Optional<Member> findByName(String name);
}
