package com.example.homepage.recruit.repository;

import com.example.homepage.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
    Optional<Recruit> findFirstByOrderByIdAsc();
}
