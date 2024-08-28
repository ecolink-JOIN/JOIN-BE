package com.join.core.study.controller;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.StudyDescriptionRequest;
import com.join.core.study.dto.request.StudyInfoRequest;
import com.join.core.study.service.StudyRecruitService;
import com.join.core.auth.domain.UserPrincipal;
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
    @Operation(summary = "스터디 기본 정보 임시 저장 - 인증 필수",
            description = "스터디 기본 정보 임시 저장 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/info")
    public ApiResponse<Void> saveStudyInfo(
            @RequestBody StudyInfoRequest infoRequest,
            @AuthenticationPrincipal UserPrincipal principal) {
        studyRecruitService.saveStudyInfo(infoRequest, principal.getAvatarToken());
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 최종 생성 - 인증 필수",
            description = "스터디 기본 정보 및 소개 포함 스터디 생성 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ApiResponse<Void> createStudy(@AuthenticationPrincipal UserPrincipal principal,
                                         @RequestBody StudyDescriptionRequest descriptionRequest) {
        studyRecruitService.createStudy(principal.getAvatarToken(), descriptionRequest);
        return ApiResponse.ok();
    }

}
