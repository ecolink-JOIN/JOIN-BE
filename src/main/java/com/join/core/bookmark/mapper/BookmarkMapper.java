package com.join.core.bookmark.mapper;

import com.join.core.bookmark.dto.response.AvatarRatingResponse;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.study.domain.Study;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkStudyReadResponse toBookmarkStudyReadResponse(Study study, double memberAverage, boolean isBookmark) {
        return new BookmarkStudyReadResponse(
                study.getStudyToken(),
                study.getTitle(),
                isBookmark,
                study.getViewCnt(),
                new AvatarRatingResponse(
                        study.getWriter().getNickname(),
                        study.getWriter().getTotalRating()
                ),
                memberAverage
        );
    }
}
