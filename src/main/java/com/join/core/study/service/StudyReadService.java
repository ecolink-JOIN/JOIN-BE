package com.join.core.study.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.domain.BookmarkReader;
import com.join.core.category.domain.Category;
import com.join.core.category.service.CategoryReader;
import com.join.core.enrollment.domain.Enrollment;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.schedule.dto.response.StudyScheduleResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.CustomStudyResponse;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import com.join.core.study.dto.response.SearchResponse;
import com.join.core.study.dto.response.StudyDetailResponse;
import com.join.core.study.dto.response.StudyListForBlockResponse;
import com.join.core.study.mapper.StudyMapper;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.service.dto.CustomStudyCommand;
import com.join.core.study.service.dto.SearchCommand;
import com.join.core.study.service.dto.StudyOrderByPopularityCommand;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudyReadService {

    private final StudyReader studyReader;
    private final CategoryReader categoryReader;
    private final EnrollmentReader enrollmentReader;
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
        Category category = getCategoryByName(command.categoryName());
        Avatar avatar = getAvatarById(command.userPrincipal());
        return studyReader.getStudyOrderByPopularity(
                new EssentialStudyCondition(category, command.form()), command.now(), command.pageable()
            )
            .map(study -> {
                double averageRating = enrollmentReader.getAverageByStudyId(study.getId());
                boolean isBookmark = isBookmark(avatar, study);
                Avatar studyLeader = enrollmentReader.getLeaderByStudyId(study.getId());
                return studyMapper.toPopularStudyReadResponse(study, studyLeader, isBookmark, averageRating);
            });
    }

    private Category getCategoryByName(String categoryName) {
        if (categoryName == null) {
            return null;
        }
        return categoryReader.getCategoryByName(categoryName);
    }

    private Avatar getAvatarById(UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return null;
        }
        return avatarReader.getAvatarById(userPrincipal.getAvatarId());
    }

    private boolean isBookmark(Avatar avatar, Study study) {
        if (avatar == null) {
            return false;
        }
        return bookmarkReader.isBookmark(study, avatar);
    }

    @Transactional(readOnly = true)
    public Collection<CustomStudyResponse> recommendStudies(CustomStudyCommand command) {
        Avatar avatar = getAvatarById(command.userPrincipal());
        Category category = getCategoryByName(command.category());
        return studyReader.getStudiesOrderByRecommendations(
                studyMapper.toEssentialStudyCondition(category, command.form()),
                studyMapper.toCustomStudyCondition(command)
            ).stream()
            .map(study -> {
                double averageRating = enrollmentReader.getAverageByStudyId(study.getId());
                boolean isBookmark = isBookmark(avatar, study);
                Avatar studyLeader = enrollmentReader.getLeaderByStudyId(study.getId());
                return studyMapper.toCustomStudyResponse(study, studyLeader, isBookmark, averageRating);
            })
            .toList();
    }

    @Transactional(readOnly = true)
    public Page<SearchResponse> search(SearchCommand command) {
        Avatar avatar = getAvatarById(command.userPrincipal());
        return studyReader.getStudiesByTitleContaining(command.keyword(), command.pageable())
            .map(study -> {
                double averageRating = enrollmentReader.getAverageByStudyId(study.getId());
                boolean isBookmark = isBookmark(avatar, study);
                Avatar studyLeader = enrollmentReader.getLeaderByStudyId(study.getId());
                return studyMapper.toSearchResponse(study, studyLeader, isBookmark, averageRating);
            });
    }

    @Transactional(readOnly = true)
    public Collection<StudyListForBlockResponse> getStudiesForBlock(String avatarToken) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(avatarToken);
        return studyReader.getStudiesByAvatarId(avatar.getId()).stream()
            .map(study -> {
                List<Avatar> enrollments = enrollmentReader.getByStudyId(study.getId()).stream()
                    .map(Enrollment::getAvatar)
                    .toList();
                return studyMapper.toStudyListForBlockResponse(study, enrollments, avatar.getId());
            }).toList();
    }
}