package com.join.core.study;

import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.AvatarRatingResponse;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import org.springframework.stereotype.Component;

@Component
public class StudyMapper {

    public PopularStudyReadResponse toPopularStudyReadResponse(Study study, boolean isBookmark, double averageRating) {
        return new PopularStudyReadResponse(
                study.getStudyToken(),
                study.getTitle(),
                isBookmark,
                study.getViewCnt(),
                new AvatarRatingResponse(
                        study.getWriter().getNickname(),
                        study.getWriter().getTotalRating()
                ),
                averageRating
        );
    }
}
