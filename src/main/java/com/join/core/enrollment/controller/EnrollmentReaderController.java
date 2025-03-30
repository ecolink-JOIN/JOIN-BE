package com.join.core.enrollment.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.enrollment.controller.specification.EnrollmentReaderControllerSpecification;
import com.join.core.enrollment.dto.response.ProofAndAttendanceStatusResponse;
import com.join.core.enrollment.service.EnrollmentReadService;
import com.join.core.enrollment.service.dto.ParticipationDetailsParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/enrollments")
public class EnrollmentReaderController implements EnrollmentReaderControllerSpecification {

    private final EnrollmentReadService enrollmentReadService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{targetToken}")
    public ApiResponse<ProofAndAttendanceStatusResponse> getParticipationDetails(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable String targetToken
    ) {
        return ApiResponse.ok(enrollmentReadService.getMemberParticipationDetails(
                new ParticipationDetailsParams(
                        principal.getAvatarToken(),
                        targetToken,
                        studyToken
                )
        ));
    }
}
