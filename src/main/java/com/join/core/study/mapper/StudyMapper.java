package com.join.core.study.mapper;

import com.join.core.avatar.domain.Avatar;
import com.join.core.category.domain.Category;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.AvatarRatingResponse;
import com.join.core.study.dto.response.CustomStudyResponse;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import com.join.core.study.dto.response.SearchResponse;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.service.dto.CustomStudyCommand;
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

    public CustomStudyResponse toCustomStudyResponse(Study study, Avatar studyLeader, boolean isBookmark, double averageRating) {
        return new CustomStudyResponse(
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

    public EssentialStudyCondition toEssentialStudyCondition(Category category, StudyForm studyForm) {
        return new EssentialStudyCondition(category, studyForm);
    }

    public CustomStudyCondition toCustomStudyCondition(CustomStudyCommand command) {
        return new CustomStudyCondition(
                command.possibleDays(),
                command.timeZone(),
                command.minParticipationCount(),
                command.maxParticipationCount(),
                command.province(),
                command.city()
        );
    }

    public SearchResponse toSearchResponse(Study study, Avatar studyLeader, boolean isBookmark, double averageRating) {
        return new SearchResponse(
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
