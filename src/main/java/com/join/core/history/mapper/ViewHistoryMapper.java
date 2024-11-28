package com.join.core.history.mapper;

import com.join.core.history.dto.response.AvatarRatingResponse;
import com.join.core.history.dto.response.ViewStudyReadResponse;
import com.join.core.study.domain.Study;
import org.springframework.stereotype.Component;

@Component
public class ViewHistoryMapper {

    public ViewStudyReadResponse toViewStudyReadResponse(Study study, double averageRating, boolean isBookmark) {
        return new ViewStudyReadResponse(
                study.getStudyToken(),
                study.getTitle(),
                isBookmark,
                study.getViewCnt(),
                new AvatarRatingResponse(study.getWriter().getNickname(), study.getWriter().getTotalRating()),
                averageRating
        );
    }
}
