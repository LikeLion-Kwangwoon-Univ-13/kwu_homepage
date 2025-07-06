package com.example.homepage.Member.repository;

import com.example.homepage.Member.entity.MembersStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersStacksRepository extends JpaRepository<MembersStacks, Long> {
}
