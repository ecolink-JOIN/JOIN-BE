package com.join.core.attendance.controller;

import com.join.core.attendance.controller.specification.AttendanceControllerSpecification;
import com.join.core.attendance.dto.CreateAttendanceRequest;
import com.join.core.attendance.dto.UpdateAttendanceRequest;
import com.join.core.attendance.service.AttendanceService;
import com.join.core.attendance.service.dto.CreateAttendanceParams;
import com.join.core.attendance.service.dto.UpdateAttendanceParams;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/meetings/{meetingNo}/attendances")
public class AttendanceController implements AttendanceControllerSpecification {

    private final AttendanceService attendanceService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> createAttendance(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo,
            @RequestBody CreateAttendanceRequest request
    ) {
        attendanceService.createAttendance(
                new CreateAttendanceParams(
                        principal.getAvatarId(),
                        studyToken,
                        meetingNo,
                        request.now()
                )
        );
        return ApiResponse.ok();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{attendanceId}")
    public ApiResponse<Void> updateAttendance(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo,
            @PathVariable Long attendanceId,
            @RequestBody UpdateAttendanceRequest request
    ) {
        attendanceService.updateAttendance(new UpdateAttendanceParams(
                principal.getAvatarToken(),
                request.status(),
                meetingNo,
                request.targetAvatarToken(),
                studyToken));
        return ApiResponse.ok();
    }

}
