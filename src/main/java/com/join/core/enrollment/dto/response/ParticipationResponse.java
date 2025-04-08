package com.join.core.enrollment.dto.response;

public record ParticipationResponse(
        String memberToken,
        String nickname,
        double attendanceRate,
        double proofRate,
        int totalFine
) {}
