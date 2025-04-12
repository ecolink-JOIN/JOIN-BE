package com.join.core.enrollment.repository;

import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.Enrollment;
import com.join.core.enrollment.dto.response.ParticipationResponse;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.fine.domain.FineReader;
import com.join.core.proof.service.ProofRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EnrollmentReaderImpl implements EnrollmentReader {

    private final EnrollmentQueryRepository enrollmentQueryRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceRateService attendanceRateService;
    private final ProofRateService proofRateService;
    private final FineReader fineReader;

    @Override
    public double getAverageByStudyId(Long studyId) {
        return enrollmentQueryRepository.getMemberAverageByStudyId(studyId);
    }

    @Override
    public Avatar getLeaderByStudyId(Long studyId) {
        return enrollmentQueryRepository.getLeaderByStudyId(studyId);
    }

    @Override
    public List<Enrollment> findJoinedEnrollmentByStudyId(Long studyId) {
        return enrollmentRepository.findEnrollmentByStudyIdAndStatus(studyId, EnrollmentStatus.JOINED);
    }

    @Override
    public boolean existEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId) {
        return enrollmentRepository.existsByAvatarIdAndStudyIdAndStatus(avatarId, studyId, EnrollmentStatus.JOINED);
    }

    @Override
    public Enrollment getEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId) {
        return enrollmentRepository.findByAvatarIdAndStudyId(avatarId, studyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_MEMBER_OF_STUDY));
    }

    @Override
    public List<Enrollment> getByStudyId(Long studyId) {
        return enrollmentRepository.findByStudyId(studyId);
    }

    @Override
    public void validateEnrollment(Long avatarId, String studyToken) {
        boolean exists = enrollmentRepository.existsByAvatarIdAndStudyStudyTokenAndStatusNot(avatarId, studyToken, EnrollmentStatus.PENDING);
        if (!exists) {
            throw new InvalidParamException(ErrorCode.INVALID_PARAMETER, "스터디 참여자가 아닙니다.");
        }
    }
  
    @Override
    public List<Avatar> getJoinedAvatarsByStudyId(Long studyId) {
        return enrollmentRepository.findEnrollmentByStudyIdAndStatus(studyId, EnrollmentStatus.JOINED)
                .stream()
                .map(Enrollment::getAvatar)
                .toList();
    }

    @Override
    public List<ParticipationResponse> getParticipationDetailsByStudy(Long studyId) {
        List<Enrollment> enrollments = enrollmentRepository
                .findEnrollmentByStudyIdAndStatus(studyId, EnrollmentStatus.JOINED);

        return enrollments.stream()
                .map(enrollment -> {
                    Avatar avatar = enrollment.getAvatar();
                    Long avatarId = avatar.getId();

                    return new ParticipationResponse(
                            avatar.getAvatarToken(),
                            avatar.getNickname(),
                            attendanceRateService.calculateIndividualAttendanceRate(avatarId),
                            proofRateService.calculateIndividualProofRate(avatarId),
                            fineReader.getTotalFineAmountByAvatar(studyId, avatarId)
                    );
                })
                .toList();
    }
    
}
