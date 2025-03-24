package com.join.core.study.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.controller.specification.StudyRuleApiSpecification;
import com.join.core.study.dto.request.UpdateStudyRuleRequest;
import com.join.core.study.dto.response.StudyRuleResponse;
import com.join.core.study.service.StudyRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class StudyRuleController implements StudyRuleApiSpecification {

    private final StudyRuleService studyRuleServices;

    @GetMapping("/{studyToken}/rules")
    @Override
    public ApiResponse<StudyRuleResponse> getRules(@PathVariable("studyToken") String studyToken) {
        return ApiResponse.ok(studyRuleServices.getRules(studyToken));
    }

    @PutMapping("/{studyToken}/rules")
    @PreAuthorize("isAuthenticated()")
    @Override
    public ApiResponse<Void> updateRules(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("studyToken") String studyToken,
            @RequestBody UpdateStudyRuleRequest updateStudyRuleRequest) {
        studyRuleServices.updateRules(principal.getAvatarId(), studyToken, updateStudyRuleRequest);
        return ApiResponse.ok();
    }
}