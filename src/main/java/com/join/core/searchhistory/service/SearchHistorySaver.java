package com.join.core.searchhistory.service;

import com.join.core.searchhistory.domain.SearchHistory;

public interface SearchHistorySaver {

    SearchHistory save(SearchHistory searchHistory);
}
