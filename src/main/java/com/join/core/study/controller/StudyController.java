package com.join.core.study.controller;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.StudyDescriptionRequest;
import com.join.core.study.dto.request.StudyInfoRequest;
import com.join.core.study.service.StudyRecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/info")
    public ApiResponse<Void> saveStudyInfo(@RequestBody StudyInfoRequest infoRequest, @RequestParam Long avatarId) { // avatarId 파라미터 X
        studyRecruitService.saveStudyInfo(infoRequest, avatarId);
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 최종 생성 - 인증 필수",
            description = "스터디 기본 정보 및 소개 포함 스터디 생성 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PostMapping("/register")
    public ApiResponse<Void> registerStudy(@RequestParam Long avatarId, @RequestBody StudyDescriptionRequest descriptionRequest) {
        studyRecruitService.createStudy(avatarId, descriptionRequest);
        return ApiResponse.ok();
    }

}

