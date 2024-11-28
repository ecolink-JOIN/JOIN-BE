package com.join.core.history.dto.response;

public record ViewStudyReadResponse(
        String studyToken,
        String title,
        boolean isBookmark,
        int viewCount,
        AvatarRatingResponse leader,
        double memberAverage
) {
}
