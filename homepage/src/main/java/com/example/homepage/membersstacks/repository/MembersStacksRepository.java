package com.example.homepage.membersstacks.repository;

import com.example.homepage.membersstacks.entity.MembersStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersStacksRepository extends JpaRepository<MembersStacks, Long> {
}
