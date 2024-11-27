package com.join.core.bookmark.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.bookmark.service.BookmarkReadService;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/bookmarks")
public class BookmarkReadController {

    private final BookmarkReadService bookmarkReadService;

    @GetMapping
    public ApiResponse<List<BookmarkStudyReadResponse>> getBookmarkStudy(@AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.ok(bookmarkReadService.getBookmarkStudy(principal.getAvatarId()));
    }
}
