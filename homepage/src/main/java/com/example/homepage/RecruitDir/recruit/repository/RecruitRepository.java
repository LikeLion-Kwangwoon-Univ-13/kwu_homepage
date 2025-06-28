package com.example.homepage.RecruitDir.recruit.repository;

import com.example.homepage.RecruitDir.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
    Optional<Recruit> findFirstByOrderByIdAsc();
}
