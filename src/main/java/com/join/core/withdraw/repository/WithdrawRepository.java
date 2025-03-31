package com.join.core.withdraw.repository;

import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import com.join.core.withdraw.domain.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    boolean existsByAvatarAndStudy(Avatar avatar, Study study);
    Optional<Withdraw> findByIdAndStudy(Long withdrawId, Study study);
}
