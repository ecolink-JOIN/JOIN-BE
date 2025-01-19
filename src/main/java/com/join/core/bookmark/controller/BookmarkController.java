package com.join.core.bookmark.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.bookmark.controller.specification.BookmarkApiSpecification;
import com.join.core.bookmark.domain.BookmarkService;
import com.join.core.bookmark.dto.request.BookmarkRequest;
import com.join.core.common.response.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/bookmarks")
public class BookmarkController implements BookmarkApiSpecification {

    private final BookmarkService bookmarkService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> addBookmark(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody BookmarkRequest bookmarkRequest
    ) {
        bookmarkService.addBookmark(bookmarkRequest.studyId(), principal.getAvatarId());
        return ApiResponse.ok();
    }

}
