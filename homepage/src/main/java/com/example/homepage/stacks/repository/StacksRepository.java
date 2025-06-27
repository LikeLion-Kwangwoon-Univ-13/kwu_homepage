package com.example.homepage.stacks.repository;

import com.example.homepage.stacks.entity.Stacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StacksRepository extends JpaRepository<Stacks, Long> {
    Optional<Stacks> findByName(String name);
}
