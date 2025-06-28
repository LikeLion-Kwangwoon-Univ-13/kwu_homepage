package com.example.homepage.MemberDir.membersstacks.repository;

import com.example.homepage.MemberDir.membersstacks.entity.MembersStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersStacksRepository extends JpaRepository<MembersStacks, Long> {
}
