package com.join.core.study.dto.response;

public record PopularStudyReadResponse(
        String studyToken,
        String title,
        boolean isBookmark,
        int viewCount,
        AvatarRatingResponse leader,
        double memberAverage
) {
}
