package com.example.homepage.Member.repository;

import com.example.homepage.Member.entity.MemberStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.homepage.Member.entity.Stack;
import java.util.List;

@Repository
public interface MemberStacksRepository extends JpaRepository<MemberStacks, Long> {
    void deleteAllByMemberId(Long memberId);

    @Query("SELECT ms.stack FROM MemberStacks ms WHERE ms.member.id = :memberId")
    List<Stack> findStacksByMemberId(@Param("memberId") Long memberId);
}
