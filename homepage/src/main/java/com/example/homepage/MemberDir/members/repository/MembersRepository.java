package com.example.homepage.MemberDir.members.repository;

import com.example.homepage.MemberDir.members.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
    List<Members> findByGeneration_Generation(int generation);
    Optional<Members> findByName(String name);
}
