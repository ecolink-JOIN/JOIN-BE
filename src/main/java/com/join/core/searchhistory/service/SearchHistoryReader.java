package com.join.core.searchhistory.service;

import com.join.core.searchhistory.domain.SearchHistory;

import java.util.Collection;

public interface SearchHistoryReader {

    Collection<SearchHistory> findByAvatarId(Long avatarId);
}
