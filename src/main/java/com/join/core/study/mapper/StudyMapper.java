package com.join.core.study.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.join.core.avatar.domain.Avatar;
import com.join.core.category.domain.Category;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.AvatarRatingResponse;
import com.join.core.study.dto.response.AvatarResponse;
import com.join.core.study.dto.response.CustomStudyResponse;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import com.join.core.study.dto.response.SearchResponse;
import com.join.core.study.dto.response.StudyListForBlockResponse;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.service.dto.CustomStudyCommand;

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

    public StudyListForBlockResponse toStudyListForBlockResponse(Study study, Collection<Avatar> enrollments, Long avatarId) {
        List<AvatarResponse> avatarResponses = enrollments.stream()
            .filter(enrollment -> enrollment.isSameAvatar(avatarId))
            .map(avatar -> new AvatarResponse(avatar.getNickname(), avatar.getAvatarToken()))
            .toList();

        return new StudyListForBlockResponse(
                study.getTitle(),
                study.getStudyToken(),
                avatarResponses,
                study.isActive()
        );
    }
}
