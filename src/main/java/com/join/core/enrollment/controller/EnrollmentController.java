package com.join.core.enrollment.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.enrollment.controller.specification.EnrollmentControllerSpecification;
import com.join.core.enrollment.dto.request.DelegateLeaderRequest;
import com.join.core.enrollment.service.EnrollmentService;
import com.join.core.enrollment.service.dto.DelegateLeaderParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/enrollments")
public class EnrollmentController implements EnrollmentControllerSpecification {

    private final EnrollmentService enrollmentService;

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/delegate")
    public ApiResponse<Void> delegateStudyLeader(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @RequestBody DelegateLeaderRequest request
    ) {
        enrollmentService.delegateStudyLeader(
                new DelegateLeaderParams(
                        principal.getAvatarToken(),
                        studyToken,
                        request.targetToken()
                )
        );
        return ApiResponse.ok();
    }
}
