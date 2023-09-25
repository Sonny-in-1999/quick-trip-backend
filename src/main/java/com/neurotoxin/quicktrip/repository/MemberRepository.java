package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
