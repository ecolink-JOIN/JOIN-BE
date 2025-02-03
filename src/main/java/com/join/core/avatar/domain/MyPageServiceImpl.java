package com.join.core.avatar.domain;

import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.avatar.dto.response.MyManagedStudyInfoResponse;
import com.join.core.avatar.dto.response.MyPageInfoResponse;
import com.join.core.proof.service.ProofRateService;
import com.join.core.proof.service.ProofReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageServiceImpl implements MyPageService {

    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
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
}
