package com.join.core.avatar.domain;

import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.avatar.dto.response.MyJoinedStudyResponse;
import com.join.core.avatar.dto.response.MyManagedStudyInfoResponse;
import com.join.core.avatar.dto.response.MyPageInfoResponse;
import com.join.core.bookmark.domain.BookmarkReader;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.proof.service.ProofRateService;
import com.join.core.proof.service.ProofReader;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.CustomStudyResponse;
import com.join.core.study.mapper.StudyMapper;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageServiceImpl implements MyPageService {

    private final AvatarReader avatarReader;
    private final BookmarkReader bookmarkReader;
    private final StudyReader studyReader;
    private final StudyMapper studyMapper;
    private final EnrollmentReader enrollmentReader;
    private final AttendanceRateService attendanceRateService;
    private final ProofReader proofReader;
    private final ProofRateService proofRateService;

    @Transactional(readOnly = true)
    @Override
    public MyPageInfoResponse getMyPageInfo(Long avatarId) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);

        double averageAttendanceRate = attendanceRateService.calculateIndividualAttendanceRate(avatarId);

        double averageProofRate = proofRateService.calculateIndividualProofRate(avatarId);

        return MyPageInfoResponse.of(avatar, averageAttendanceRate, averageProofRate);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MyManagedStudyInfoResponse> getMyManagedStudies(Long avatarId) {
        List<Study> managedStudies = studyReader.getStudiesByLeaderAvatarId(avatarId);

        return managedStudies.stream().map(
                study -> {
                    double teamAttendanceRateForStudy = attendanceRateService.calculateTeamAttendanceRateForStudy(study.getId());
                    double teamProofRateForStudy = proofRateService.calculateTeamAttendanceRateForStudy(study.getId());
                    List<MyManagedStudyInfoResponse.StudyMemberAchievementDto> achievementDtos = getMembersRatesAndApprovedStatus(study);

                    return MyManagedStudyInfoResponse.of(study, teamAttendanceRateForStudy, teamProofRateForStudy, achievementDtos);
                }
        ).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public MyJoinedStudyResponse getMyJoinedStudies(Long avatarId) {
        List<Study> joinedStudiesByAvatarId = studyReader.getJoinedStudiesByAvatarId(avatarId);
        return MyJoinedStudyResponse.of(joinedStudiesByAvatarId);
    }

    private List<MyManagedStudyInfoResponse.StudyMemberAchievementDto> getMembersRatesAndApprovedStatus(Study study) {
        List<Avatar> avatars = avatarReader.findAvatarsExceptPendingByStudyId(study.getId());
        return avatars.stream()
                .map(avatar -> {
                    double attendanceRate = attendanceRateService.calculateMemberAttendanceRateForStudy(avatar.getId(), study.getId());
                    double proofRate = proofRateService.calculateMembersAttendanceRateForStudy(avatar.getId(), study.getId());
                    boolean isFullyApproved = proofReader.isFullyApproved(avatar.getId(), study.getId());
                    return MyManagedStudyInfoResponse.StudyMemberAchievementDto.of(avatar, attendanceRate, proofRate, isFullyApproved);
                }).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<CustomStudyResponse> getMyInterestStudies(Long avatarId) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        List<Study> interestStudies = studyReader.getInterestStudiesByAvatarId(avatarId);

        return interestStudies.stream()
                .map(study -> {
                    double averageRating = enrollmentReader.getAverageByStudyId(study.getId());
                    boolean isBookmark = isBookmark(avatar, study);
                    Avatar studyLeader = enrollmentReader.getLeaderByStudyId(study.getId());
                    return studyMapper.toCustomStudyResponse(study, studyLeader, isBookmark, averageRating);
                })
                .toList();
    }

    private boolean isBookmark(Avatar avatar, Study study) {
        if (avatar == null) {
            return false;
        }
        return bookmarkReader.isBookmark(study, avatar);
    }
}
