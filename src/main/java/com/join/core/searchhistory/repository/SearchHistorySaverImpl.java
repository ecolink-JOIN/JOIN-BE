package com.join.core.searchhistory.repository;

import com.join.core.searchhistory.domain.SearchHistory;
import com.join.core.searchhistory.service.SearchHistorySaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchHistorySaverImpl implements SearchHistorySaver {

    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public SearchHistory save(SearchHistory searchHistory) {
        return searchHistoryRepository.save(searchHistory);
    }
}
