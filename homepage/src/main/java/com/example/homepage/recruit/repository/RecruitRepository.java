package com.example.homepage.recruit.repository;

import com.example.homepage.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
}
