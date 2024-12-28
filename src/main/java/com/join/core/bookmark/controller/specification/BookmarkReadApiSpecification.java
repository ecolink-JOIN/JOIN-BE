package com.join.core.bookmark.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface BookmarkReadApiSpecification {

    @Tag(name = "${swagger.tag.bookmark}")
    @Operation(summary = "북마크한 스터디 조회 - 인증 필수",
            description = "북마크한 스터디 조회",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Page<BookmarkStudyReadResponse>> getBookmarkStudy(
            @AuthenticationPrincipal UserPrincipal principal,
            PageParameterRequest pageParameterRequest
    );
}
