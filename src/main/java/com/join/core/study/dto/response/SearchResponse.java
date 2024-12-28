package com.join.core.study.dto.response;

public record SearchResponse (
        String studyToken,
        String title,
        boolean isBookmark,
        int viewCount,
        AvatarRatingResponse leader,
        double memberAverage
) {
}
