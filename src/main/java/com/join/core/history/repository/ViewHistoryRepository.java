package com.join.core.history.repository;

import com.join.core.history.domain.ViewHistory;
import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    @Query("SELECT study " +
            "FROM ViewHistory " +
            "WHERE avatar.id = :avatarId " +
            "GROUP BY study.id " +
            "ORDER BY MAX(createdDate) ASC")
    Page<Study> findDistinctStudiesByAvatarId(Long avatarId, Pageable pageable);
}
