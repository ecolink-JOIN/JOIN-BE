package com.join.core.enrollment.dto.response;

import java.util.Collection;

public record ProofAndAttendanceStatusResponse(
        String studyToken,
        String avatarToken,
        Collection<MeetingAttendanceStatus> meetingAttendanceStatus
) {
}
