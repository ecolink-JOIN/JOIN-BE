package com.join.core.study.service;

import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.domain.BookmarkReader;
import com.join.core.category.domain.Category;
import com.join.core.category.service.CategoryReader;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.evaluation.domain.EvaluationReader;
import com.join.core.evaluation.dto.response.EvaluationScore;
import com.join.core.proof.service.ProofRateService;
import com.join.core.proof.service.ProofReader;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.*;
import com.join.core.study.mapper.StudyMapper;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.repository.condition.SearchCondition;
import com.join.core.study.service.dto.CustomStudyCommand;
import com.join.core.study.service.dto.SearchCommand;
import com.join.core.study.service.dto.StudyMemberAchievementDto;
import com.join.core.study.service.dto.StudyOrderByPopularityCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudyReadService {

    private final StudyReader studyReader;
    private final CategoryReader categoryReader;
    private final EnrollmentReader enrollmentReader;
    private final BookmarkReader bookmarkReader;
    private final AvatarReader avatarReader;
    private final ProofReader proofReader;
    private final StudyMapper studyMapper;
    private final EvaluationReader evaluationReader;
    private final AttendanceRateService attendanceRateService;
    private final ProofRateService proofRateService;

    @Transactional(readOnly = true)
    public StudyDetailResponse getStudyDetails(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        Long writerId = study.getWriter().getId();

        EvaluationScore evaluationScore = evaluationReader.getEvaluationScores(study.getId(), writerId);

        return StudyDetailResponse.from(study, evaluationScore);
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
        Category category = getCategoryByName(command.parameter().category());
        SearchCondition condition = studyMapper.toSearchCondition(command.parameter(), category);
        return studyReader.getStudiesByTitleAndConditions(condition, command.pageable())
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
                List<AvatarResponse> enrollments = enrollmentReader.getByStudyId(study.getId()).stream()
                    .map(a -> new AvatarResponse(a.getAvatar().getNickname(), a.getAvatar().getAvatarToken()))
                    .filter(avatarResponse -> !avatar.isSameAvatar(avatarResponse.avatarToken()))
                    .toList();
                return studyMapper.toStudyListForBlockResponse(study, enrollments);
            }).toList();
    }

    public StudyStatusResponse getStudyStatus(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);

        double teamAttendanceRateForStudy = attendanceRateService.calculateTeamAttendanceRateForStudy(study.getId());
        double teamProofRateForStudy = proofRateService.calculateTeamAttendanceRateForStudy(study.getId());
        List<StudyMemberAchievementDto> achievementDtos = getMembersRatesAndApprovedStatus(study);

        return StudyStatusResponse.of(study, teamAttendanceRateForStudy, teamProofRateForStudy, achievementDtos);
    }

    private List<StudyMemberAchievementDto> getMembersRatesAndApprovedStatus(Study study) {
        List<Avatar> avatars = avatarReader.findAvatarsExceptPendingByStudyId(study.getId());
        return avatars.stream()
                .map(avatar -> {
                    double attendanceRate = attendanceRateService.calculateMemberAttendanceRateForStudy(avatar.getId(), study.getId());
                    double proofRate = proofRateService.calculateMembersAttendanceRateForStudy(avatar.getId(), study.getId());
                    boolean isFullyApproved = proofReader.isFullyApproved(avatar.getId(), study.getId());
                    return StudyMemberAchievementDto.of(avatar, attendanceRate, proofRate, isFullyApproved);
                }).toList();
    }
}