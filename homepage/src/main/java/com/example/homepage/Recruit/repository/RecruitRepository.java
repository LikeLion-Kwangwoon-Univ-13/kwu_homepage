package com.example.homepage.Recruit.repository;

import com.example.homepage.Recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    Optional<Recruit> findFirstByOrderByIdAsc();
}
