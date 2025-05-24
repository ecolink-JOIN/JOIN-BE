package com.join.core.searchhistory.repository;

import com.join.core.searchhistory.domain.SearchHistory;
import com.join.core.searchhistory.service.SearchHistoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class SearchHistoryReaderImpl implements SearchHistoryReader {

    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public Collection<SearchHistory> findByAvatarId(Long avatarId) {
        return searchHistoryRepository.findTop10ByAvatarIdOrderByIdDesc(avatarId);
    }

    @Override
    public boolean existsByAvatarIdAndKeyword(Long avatarId, String keyword) {
        return searchHistoryRepository.existsByAvatarIdAndKeyword(avatarId, keyword);
    }
}
