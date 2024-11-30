package com.join.core.study.service;

import com.join.core.application.domain.Application;
import com.join.core.application.repository.ApplicationReader;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.repository.BookmarkReader;
import com.join.core.category.domain.Category;
import com.join.core.category.service.CategoryReader;
import com.join.core.schedule.dto.response.StudyScheduleResponse;
import com.join.core.study.StudyMapper;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import com.join.core.study.dto.response.StudyDetailResponse;
import com.join.core.study.service.dto.StudyOrderByPopularityCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudyReadService {

    private final StudyReader studyReader;
    private final CategoryReader categoryReader;
    private final ApplicationReader applicationReader;
    private final BookmarkReader bookmarkReader;
    private final AvatarReader avatarReader;
    private final StudyMapper studyMapper;

    @Transactional(readOnly = true)
    public StudyDetailResponse getStudyDetails(Long studyId) {
        Study study = studyReader.getStudyById(studyId);

        List<StudyScheduleResponse> schedules = study.getSchedules().stream()
                .map(schedule -> new StudyScheduleResponse(
                        schedule.getWeekOfDay(),
                        schedule.getStTime(),
                        schedule.getEndTime()))
                .toList();

        return new StudyDetailResponse(
                study.getStudyName(),
                study.getTitle(),
                study.getIntroduction(),
                study.getContent(),
                study.getCapacity(),
                study.isRegular(),
                study.getRecruitEndDate(),
                study.getStDate(),
                study.getEndDate(),
                study.getWriter().getId(),
                study.getWriter().getNickname(),
                schedules,
                study.getRuleExp(),
                study.getQualificationExp()
        );
    }

    @Transactional(readOnly = true)
    public Page<PopularStudyReadResponse> getStudiesOrderByPopularity(StudyOrderByPopularityCommand command) {
        Category category = categoryReader.getCategoryByName(command.categoryName());
        Avatar avatar = getAvatarById(command.userPrincipal());
        Pageable pageable = PageRequest.of(command.page() - 1, command.size());
        return studyReader.getStudyOrderByPopularity(category.getId(), command.form(), command.now(), pageable)
                .map(study -> {
                    double averageRating = getAverageRating(study.getId());
                    boolean isBookmark = isBookmark(avatar, study);
                    return studyMapper.toPopularStudyReadResponse(study, isBookmark, averageRating);
                });
    }

    private Avatar getAvatarById(UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return null;
        }
        return avatarReader.getAvatarById(userPrincipal.getAvatarId());
    }

    private double getAverageRating(Long studyId) {
        List<Application> applications = applicationReader.getApproveApplications(studyId);
        if (applications.isEmpty()) {
            return 0;
        }
        return applications.stream()
                .mapToDouble(application -> application.getAvatar().getTotalRating())
                .sum() / applications.size();
    }

    private boolean isBookmark(Avatar avatar, Study study) {
        if (avatar == null) {
            return false;
        }
        return bookmarkReader.isBookmark(study, avatar);
    }
}
