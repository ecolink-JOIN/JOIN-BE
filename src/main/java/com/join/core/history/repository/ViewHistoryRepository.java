package com.join.core.history.repository;

import com.join.core.history.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    List<ViewHistory> findByAvatarId(Long avatarId);
}
