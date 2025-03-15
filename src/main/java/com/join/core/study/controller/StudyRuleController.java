package com.join.core.study.controller;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.controller.specification.StudyRuleApiSpecification;
import com.join.core.study.dto.response.StudyRuleResponse;
import com.join.core.study.service.StudyRuleService;
import lombok.RequiredArgsConstructor;
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
}