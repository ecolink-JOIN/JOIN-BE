package com.join.core.attendance.controller;

import com.join.core.attendance.dto.response.CheckAttendanceResponse;
import com.join.core.attendance.service.AttendanceReadService;
import com.join.core.attendance.service.command.CheckAttendanceCommand;
import com.join.core.auth.domain.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/meetings/{meetingNo}/attendance")
public class AttendanceReadController {

    private final AttendanceReadService attendanceReadService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public CheckAttendanceResponse checkAttendance(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo
    ) {
        return attendanceReadService.checkAttendance(
                new CheckAttendanceCommand(
                        userPrincipal.getAvatarId(),
                        studyToken,
                        meetingNo
                )
        );
    }
}
