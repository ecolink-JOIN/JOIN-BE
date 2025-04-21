package com.join.core.searchhistory.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.searchhistory.dto.response.RecentSearchResponse;
import com.join.core.searchhistory.mapper.SearchHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class SearchHistoryReadService {

    private final SearchHistoryReader searchHistoryReader;
    private final AvatarReader avatarReader;
    private final SearchHistoryMapper searchHistoryMapper;

    @Transactional(readOnly = true)
    public Collection<RecentSearchResponse> findByAvatarId(String avatarToken) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(avatarToken);

        return searchHistoryReader.findByAvatarId(avatar.getId()).stream()
                .map(searchHistoryMapper::mapToRecentSearchResponse)
                .toList();
    }
}
