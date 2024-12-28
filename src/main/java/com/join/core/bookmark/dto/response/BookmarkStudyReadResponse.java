package com.join.core.bookmark.dto.response;

public record BookmarkStudyReadResponse(
        String studyToken,
        String title,
        boolean isBookmark,
        int viewCount,
        AvatarRatingResponse leader,
        double memberAverage
) {
}
