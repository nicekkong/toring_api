package com.cplanet.toring.repository;

import com.cplanet.toring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);

    @Query(value = "select m from Member m left join m.profile p where m.id=:id")
    Optional<Member> findByIdWithProfile(@Param(value="id")Long id);

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);
}
