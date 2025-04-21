package com.join.core.searchhistory.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.searchhistory.dto.response.RecentSearchResponse;
import com.join.core.searchhistory.service.SearchHistoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/search-histories")
public class SearchHistoryReadController {

    private final SearchHistoryReadService searchHistoryReadService;

    @GetMapping
    public Collection<RecentSearchResponse> findByAvatarId(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return searchHistoryReadService.findByAvatarId(userPrincipal.getAvatarToken());
    }
}
