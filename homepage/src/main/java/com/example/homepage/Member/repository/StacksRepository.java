package com.example.homepage.Member.repository;

import com.example.homepage.Member.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StacksRepository extends JpaRepository<Stack, Long> {
    Optional<Stack> findByName(String name);
}
