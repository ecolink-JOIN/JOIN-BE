package com.join.core.bookmark.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.bookmark.dto.request.BookmarkAddRequest;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface BookmarkApiSpecification {

    @Tag(name = "${swagger.tag.bookmark}")
    @Operation(summary = "스터디 북마크 등록 API - 인증 필수",
            description = "스터디 북마크 등록 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> addBookmark(
            @AuthenticationPrincipal UserPrincipal principal,
            BookmarkAddRequest bookmarkAddRequest
    );

}
