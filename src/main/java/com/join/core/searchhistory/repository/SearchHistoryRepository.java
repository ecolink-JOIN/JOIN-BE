package com.join.core.searchhistory.repository;

import com.join.core.searchhistory.domain.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    Collection<SearchHistory> findTop10ByAvatarIdOrderByIdDesc(Long avatarId);
}
