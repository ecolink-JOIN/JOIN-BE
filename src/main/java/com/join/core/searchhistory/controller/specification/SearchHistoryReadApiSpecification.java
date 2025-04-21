package com.join.core.searchhistory.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.searchhistory.dto.response.RecentSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collection;

public interface SearchHistoryReadApiSpecification {

    @Tag(name = "${swagger.tag.search}")
    @Operation(summary = "검색 내역 조회 - 인증 필수",
            description = "검색 내역 조회",
            security = {@SecurityRequirement(name = "session-token")})
    Collection<RecentSearchResponse> findByAvatarId(@AuthenticationPrincipal UserPrincipal userPrincipal);
}
