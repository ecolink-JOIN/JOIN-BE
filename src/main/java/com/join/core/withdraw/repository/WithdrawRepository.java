package com.join.core.withdraw.repository;

import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import com.join.core.withdraw.domain.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    boolean existsByAvatarAndStudy(Avatar avatar, Study study);

}
