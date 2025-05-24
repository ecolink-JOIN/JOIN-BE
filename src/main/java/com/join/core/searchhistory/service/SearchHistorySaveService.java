package com.join.core.searchhistory.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.searchhistory.mapper.SearchHistoryMapper;
import com.join.core.searchhistory.service.dto.SearchContentParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchHistorySaveService {

    private final SearchHistorySaver searchHistorySaver;
    private final SearchHistoryReader searchHistoryReader;
    private final AvatarReader avatarReader;
    private final SearchHistoryMapper searchHistoryMapper;

    public void save(SearchContentParameter parameter) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(parameter.avatarToken());
        if (!searchHistoryReader.existsByAvatarIdAndKeyword(avatar.getId(), parameter.keyword())) {
            searchHistorySaver.save(searchHistoryMapper.mapToSearchHistory(avatar, parameter.keyword()));
        }
    }
}
