package com.example.homepage.MemberDir.membersstacks.entity;

import com.example.homepage.MemberDir.members.entity.Members;
import com.example.homepage.MemberDir.stacks.entity.Stacks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersStacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;

    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stacks stack;
}
