package com.join.core.bookmark.dto.response;

public record BookmarkStudyReadResponse(
        String studyToken,
        String title,
        int viewCount,
        AvatarRatingResponse leader,
        double memberAverage
) {
}
