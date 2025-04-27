package com.join.core.study.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.StudyEndRequest;
import com.join.core.study.dto.request.StudyReRecruitRequest;
import com.join.core.study.dto.request.StudyRecruitRequest;
import com.join.core.study.service.StudyEndService;
import com.join.core.study.service.StudyRecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class StudyController {

    private final StudyRecruitService studyRecruitService;
    private final StudyEndService studyEndService;

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 모집 - 인증 필수",
            description = "스터디 모집 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/recruit")
    public ApiResponse<Void> createStudy(@AuthenticationPrincipal UserPrincipal principal,
                                         @RequestBody StudyRecruitRequest recruitRequest) {
        studyRecruitService.createStudy(principal.getAvatarId(), recruitRequest);
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 추가 모집 - 인증 필수",
            description = "스터디 추가 모집 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/re-recruit")
    public ApiResponse<Void> reRecruitStudy(@AuthenticationPrincipal UserPrincipal principal,
                                            @RequestParam String studyToken,
                                            @RequestBody StudyReRecruitRequest reRecruitRequest) {
        studyRecruitService.reRecruitStudy(principal.getAvatarId(), studyToken, reRecruitRequest);
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 종료 - 인증 필수",
            description = "스터디 종료 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{studyToken}/close")
    public ApiResponse<Void> closeStudy(@AuthenticationPrincipal UserPrincipal principal,
                                      @PathVariable String studyToken,
                                      @RequestBody StudyEndRequest endRequest) {
        studyEndService.endStudy(studyToken, endRequest, principal);
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 모집 상태 변경 - 인증 필수",
            description = "스터디 모집 상태 변경 - 스터디 모집중(RECRUITING) ↔ 모집완료(READY) 전환",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{studyToken}/recruitment")
    public ApiResponse<Void> toggleRecruitStatus(@AuthenticationPrincipal UserPrincipal principal,
                                                 @PathVariable String studyToken) {
        studyRecruitService.toggleRecruitStatus(principal.getAvatarId(), studyToken);
        return ApiResponse.ok();
    }

}