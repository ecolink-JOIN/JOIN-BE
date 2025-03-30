package com.join.core.application.service;

import com.join.core.application.domain.Application;
import com.join.core.application.domain.ApplicationReader;
import com.join.core.application.dto.response.ApplicationReadResponse;
import com.join.core.application.dto.response.AvatarPerformance;
import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.proof.service.ProofRateService;
import com.join.core.study.domain.Study;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationReadService {

    private final ApplicationReader applicationReader;
    private final AttendanceRateService attendanceRateService;
    private final ProofRateService proofRateService;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;

    public List<ApplicationReadResponse> getApplicationDetails(String studyToken, Long avatarId) {
        List<Application> applications = applicationReader.getApplicationsByStudyToken(studyToken);

        if (applications.isEmpty()) {
            throw new BadRequestException(ErrorCode.APPLICATION_NOT_FOUND);
        }

        return applications.stream()
                .map(application -> {
                    Avatar avatar = avatarReader.getById(application.getAvatar().getId());
                    AvatarPerformance activeStudyStats = calculateAvatarPerformance(avatar.getId(), StudyStatus.ACTIVE);
                    AvatarPerformance completedStudyStats = calculateAvatarPerformance(avatar.getId(), StudyStatus.COMPLETED);

                    return ApplicationReadResponse.from(application, avatar, activeStudyStats, completedStudyStats);
                })
                .collect(Collectors.toList());
    }

    private AvatarPerformance calculateAvatarPerformance(Long avatarId, StudyStatus status) {
        List<Study> studies = studyReader.getStudiesByAvatarIdAndStatus(avatarId, status);

        if (studies.isEmpty()) {
            return new AvatarPerformance(0.0, 0.0, 0.0);
        }

        double attendanceRate = attendanceRateService.calculateIndividualAttendanceRate(avatarId);
        double proofRate = proofRateService.calculateIndividualProofRate(avatarId);
        double rating = avatarReader.getById(avatarId).getAverageEvaluation();

        return new AvatarPerformance(attendanceRate, proofRate, rating);
    }

}
