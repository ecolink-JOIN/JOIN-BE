package com.join.core.searchhistory.mapper;

import com.join.core.searchhistory.domain.SearchHistory;
import com.join.core.searchhistory.dto.response.RecentSearchResponse;
import org.springframework.stereotype.Component;

@Component
public class SearchHistoryMapper {

    public RecentSearchResponse mapToRecentSearchResponse(SearchHistory searchHistory) {
        return new RecentSearchResponse(
                searchHistory.getId(),
                searchHistory.getKeyword(),
                searchHistory.getCreatedDate()
        );
    }
}
