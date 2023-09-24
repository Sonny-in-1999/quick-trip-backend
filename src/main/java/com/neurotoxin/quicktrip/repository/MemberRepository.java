package com.neurotoxin.quicktrip.repository;

import com.neurotoxin.quicktrip.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member, Long> {
}
