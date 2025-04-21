package com.join.core.searchhistory.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.searchhistory.controller.specification.SearchHistoryReadApiSpecification;
import com.join.core.searchhistory.dto.response.RecentSearchResponse;
import com.join.core.searchhistory.service.SearchHistoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/search-histories")
public class SearchHistoryReadController implements SearchHistoryReadApiSpecification {

    private final SearchHistoryReadService searchHistoryReadService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public Collection<RecentSearchResponse> findByAvatarId(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return searchHistoryReadService.findByAvatarId(userPrincipal.getAvatarToken());
    }
}
