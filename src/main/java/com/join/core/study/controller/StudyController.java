package com.join.core.study.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.StudyReRecruitRequest;
import com.join.core.study.dto.request.StudyRecruitRequest;
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

}