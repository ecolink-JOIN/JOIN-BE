package com.join.core.study.mapper;

import com.join.core.avatar.domain.Avatar;
import com.join.core.category.domain.Category;
import com.join.core.fine.constant.FineReason;
import com.join.core.fine.domain.FineRule;
import com.join.core.schedule.dto.response.StudyScheduleResponse;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.*;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.service.dto.CustomStudyCommand;
import com.join.core.study.service.dto.FineReasonAmountsDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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

    public StudyListForBlockResponse toStudyListForBlockResponse(Study study, Collection<AvatarResponse> avatarResponses) {
        return new StudyListForBlockResponse(
                study.getTitle(),
                study.getStudyToken(),
                avatarResponses,
                study.isActive()
        );
    }

    public List<StudyScheduleResponse> toStudyScheduleResponse(Study study) {
        return study.getSchedules().stream()
                .map(studySchedule ->
                     new StudyScheduleResponse(
                            studySchedule.getWeekOfDay(),
                            studySchedule.getStTime(),
                            studySchedule.getEndTime()
                    )
                ).toList();
    }

    public FineReasonAmountsDto toFineReasonAmountsDto(Study study) {
        Integer tardiness = study.getFineRules().stream()
                .filter(fineRule -> fineRule.getReason() == FineReason.TARDINESS)
                .findFirst()
                .map(FineRule::getAmount)
                .orElse(0);
        Integer absence = study.getFineRules().stream()
                .filter(fineRule -> fineRule.getReason() == FineReason.ABSENCE)
                .findFirst()
                .map(FineRule::getAmount)
                .orElse(0);
        Integer nonProof = study.getFineRules().stream()
                .filter(fineRule -> fineRule.getReason() == FineReason.NON_PROOF)
                .findFirst()
                .map(FineRule::getAmount)
                .orElse(0);

        return new FineReasonAmountsDto(tardiness, absence, nonProof);
    }
}
