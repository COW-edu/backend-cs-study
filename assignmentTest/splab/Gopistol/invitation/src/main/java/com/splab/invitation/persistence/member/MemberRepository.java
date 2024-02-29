package com.splab.invitation.persistence.member;

import com.splab.invitation.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  public Optional<Member> findByEmail(String email);
}
