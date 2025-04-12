package com.join.core.fine.repository;

import com.join.core.fine.domain.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FineRepository extends JpaRepository<Fine, Long> {

    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM Fine f WHERE f.study.id = :studyId AND f.avatar.id = :avatarId")
    int sumTotalFineByStudyIdAndAvatarId(Long studyId, Long avatarId);
}