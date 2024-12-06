package com.join.core.bookmark.mapper;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.dto.response.AvatarRatingResponse;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.study.domain.Study;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkStudyReadResponse toBookmarkStudyReadResponse(Study study, Avatar studyLeader, double memberAverage, boolean isBookmark) {
        return new BookmarkStudyReadResponse(
                study.getStudyToken(),
                study.getTitle(),
                isBookmark,
                study.getViewCnt(),
                new AvatarRatingResponse(
                        studyLeader.getNickname(),
                        studyLeader.getTotalRating()
                ),
                memberAverage
        );
    }
}
