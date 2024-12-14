package com.join.core.bookmark.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.bookmark.controller.specification.BookmarkReadApiSpecification;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.bookmark.service.BookmarkReadService;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/bookmarks")
public class BookmarkReadController implements BookmarkReadApiSpecification {

    private final BookmarkReadService bookmarkReadService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<Page<BookmarkStudyReadResponse>> getBookmarkStudy(
            @AuthenticationPrincipal UserPrincipal principal,
            PageParameterRequest pageParameterRequest
    ) {
        return ApiResponse.ok(bookmarkReadService.getBookmarkStudy(principal.getAvatarId(), pageParameterRequest));
    }
}
