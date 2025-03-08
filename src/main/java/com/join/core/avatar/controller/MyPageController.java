package com.join.core.avatar.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.controller.specification.MyPageControllerSpecification;
import com.join.core.avatar.domain.MyPageService;
import com.join.core.avatar.dto.response.MyInterestStudyResponse;
import com.join.core.avatar.dto.response.MyJoinedStudyResponse;
import com.join.core.avatar.dto.response.MyManagedStudyInfoResponse;
import com.join.core.avatar.dto.response.MyPageInfoResponse;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/my-page")
public class MyPageController implements MyPageControllerSpecification {

    private final MyPageService myPageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ApiResponse<MyPageInfoResponse> getMyPageInfo(
			@AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.ok(myPageService.getMyPageInfo(principal.getAvatarId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/manage-study")
    public ApiResponse<List<MyManagedStudyInfoResponse>> getMyManagedStudies(
            @AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.ok(myPageService.getMyManagedStudies(principal.getAvatarId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/join-study")
    public ApiResponse<MyJoinedStudyResponse> getMyJoinedStudies(
            @AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.ok(myPageService.getMyJoinedStudies(principal.getAvatarId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/interest-study")
    public ApiResponse<MyInterestStudyResponse> getMyInterestStudies(
            @AuthenticationPrincipal UserPrincipal principal) {
        return ApiResponse.ok(myPageService.getMyInterestStudies(principal.getAvatarId()));
    }
}
