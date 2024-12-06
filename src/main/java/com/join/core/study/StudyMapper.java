package com.join.core.study;

import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.AvatarRatingResponse;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import org.springframework.stereotype.Component;

@Component
public class StudyMapper {

    public PopularStudyReadResponse toPopularStudyReadResponse(Study study, Avatar studyLeader, boolean isBookmark, double averageRating) {
        return new PopularStudyReadResponse(
                study.getStudyToken(),
                study.getTitle(),
                isBookmark,
                study.getViewCnt(),
                new AvatarRatingResponse(
                        studyLeader.getNickname(),
                        studyLeader.getTotalRating()
                ),
                averageRating
        );
    }
}
