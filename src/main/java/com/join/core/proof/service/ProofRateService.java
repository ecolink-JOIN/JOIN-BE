package com.join.core.proof.service;

import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.util.NumberUtil;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.proof.domain.Proof;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProofRateService {

    private final AvatarReader avatarReader;
    private final MeetingReader meetingReader;
    private final ProofReader proofReader;

    @Transactional(readOnly = true)
    public double calculateIndividualProofRate(Long avatarId) {
        List<Proof> proofsForJoinedStudies = proofReader.findProofsByAvatarIdForJoinedStudies(avatarId);
        List<Proof> proofsForLeftStudies = proofReader.findProofsByAvatarIdForLeftStudies(avatarId);

        long totalMeetings = meetingReader.findMeetingsByAvatarIdForStudies(avatarId).size();

        double totalProof = Stream.concat(
                        proofsForJoinedStudies.stream(),
                        proofsForLeftStudies.stream()
                )
                .mapToDouble(proof -> {
                    double reflectionRate = proof.getStatus().getReflectionRate();

                    if (proofsForLeftStudies.contains(proof)) {
                        reflectionRate *= EnrollmentStatus.LEFT.getReflectionRate();
                    }

                    return reflectionRate;
                })
                .sum();

        if (totalProof == 0) {
            return 0.0;
        }

        return NumberUtil.round(2, totalProof / totalMeetings * 100);
    }

    @Transactional(readOnly = true)
    public double calculateTeamAttendanceRateForStudy(Long studyId) {
        List<Proof> attendancesForJoinedStudies = proofReader.findByStudyIdForJoinedStudy(studyId);
        List<Proof> attendancesForLeftStudies = proofReader.findByStudyIdForLeftStudy(studyId);

        long countStudyMembers = avatarReader.findAvatarsExceptPendingByStudyId(studyId).size();
        long countMeetings = meetingReader.findMeetingsByStudyId(studyId).size();
        long totalMeetings = countMeetings * countStudyMembers;

        double totalAttendance = Stream.concat(
                        attendancesForJoinedStudies.stream(),
                        attendancesForLeftStudies.stream()
                )
                .mapToDouble(attendance -> {
                    double reflectionRate = attendance.getStatus().getReflectionRate();

                    if (attendancesForLeftStudies.contains(attendance)) {
                        reflectionRate *= EnrollmentStatus.LEFT.getReflectionRate();
                    }

                    return reflectionRate;
                })
                .sum();

        if (totalAttendance == 0) return 0.0;

        return NumberUtil.round(2, totalAttendance / totalMeetings * 100);
    }

    @Transactional(readOnly = true)
    public double calculateMembersAttendanceRateForStudy(Long avatarId, Long studyId) {
        List<Proof> proofsForJoinedStudies = proofReader.findByAvatarIdAndStudyIdForJoinedStudy(avatarId, studyId);
        List<Proof> proofsForLeftStudies = proofReader.findByAvatarIdAndStudyIdForLeftStudy(avatarId, studyId);

        long totalMeetings = meetingReader.findMeetingsByStudyId(studyId).size();

        double totalProof = Stream.concat(
                        proofsForJoinedStudies.stream(),
                        proofsForLeftStudies.stream()
                )
                .mapToDouble(proof -> {
                    double reflectionRate = proof.getStatus().getReflectionRate();

                    if (proofsForLeftStudies.contains(proof)) {
                        reflectionRate *= EnrollmentStatus.LEFT.getReflectionRate();
                    }

                    return reflectionRate;
                })
                .sum();

        if (totalProof == 0) {
            return 0.0;
        }

        return NumberUtil.round(2, totalProof / totalMeetings * 100);
    }
}
