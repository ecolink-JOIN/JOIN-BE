package com.join.core.searchhistory.mapper;

import com.join.core.avatar.domain.Avatar;
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

    public SearchHistory mapToSearchHistory(Avatar avatar, String keyword) {
        return SearchHistory.builder()
                .avatar(avatar)
                .keyword(keyword)
                .build();
    }
}
