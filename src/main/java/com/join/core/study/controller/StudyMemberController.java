package com.join.core.study.controller;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.controller.specification.StudyMemberApiSpecification;
import com.join.core.study.dto.response.StudyMemberResponse;
import com.join.core.study.service.StudyMemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class StudyMemberController implements StudyMemberApiSpecification {

    private final StudyMemberReadService studyMemberReadService;

    @PostMapping("/{studyToken}/member")
    @Override
    public ApiResponse<Collection<StudyMemberResponse>> getStudyMembers(@PathVariable String studyToken) {
        return ApiResponse.ok(studyMemberReadService.getStudyMembers(studyToken));
    }
}