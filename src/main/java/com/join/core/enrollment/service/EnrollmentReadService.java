package com.join.core.enrollment.service;

import com.join.core.attendance.constant.AttendanceStatus;
import com.join.core.attendance.domain.Attendance;
import com.join.core.attendance.service.AttendanceReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.enrollment.dto.response.MeetingAttendanceStatus;
import com.join.core.enrollment.dto.response.ProofAndAttendanceStatusResponse;
import com.join.core.enrollment.service.dto.ParticipationDetailsParams;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.proof.domain.Proof;
import com.join.core.proof.service.ProofReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EnrollmentReadService {

    private final EnrollmentReader enrollmentReader;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final MeetingReader meetingReader;
    private final ProofReader proofReader;
    private final AttendanceReader attendanceReader;


    @Transactional(readOnly = true)
    public ProofAndAttendanceStatusResponse getMemberParticipationDetails(ParticipationDetailsParams params) {
        Study study = studyReader.getStudyByToken(params.studyToken());
        checkLeaderPermissions(params.avatarToken(), study.getId());

        Avatar target = avatarReader.getAvatarByAvatarToken(params.targetToken());
        checkParticipation(target, study.getId());

        List<MeetingAttendanceStatus> result = getPastMeetings(study).stream()
                .map(meeting -> createMeetingAttendanceStatus(meeting, target))
                .toList();

        return new ProofAndAttendanceStatusResponse(
                study.getStudyToken(),
                target.getAvatarToken(),
                result);
    }

    private void checkLeaderPermissions(String avatarToken, Long studyId) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(avatarToken);
        Avatar leader = enrollmentReader.getLeaderByStudyId(studyId);
        if (!leader.isSameAvatar(avatar.getId())) {
            throw new LeaderForbiddenException(ErrorCode.LEADER_ONLY_ACCESS);
        }
    }

    private void checkParticipation(Avatar target, Long studyId) {
        if (!enrollmentReader.existEnrollmentByAvatarIdAndStudyId(target.getId(), studyId)) {
            throw new BadRequestException(ErrorCode.INVALID_MEMBER);
        }
    }

    private List<Meeting> getPastMeetings(Study study) {
        LocalDateTime now = LocalDateTime.now();
        return meetingReader.getMeetingsByStudy(study).stream()
                .filter(meeting -> meeting.isAfterStudy(now))
                .toList();
    }

    private MeetingAttendanceStatus createMeetingAttendanceStatus(Meeting meeting, Avatar avatar) {
        AttendanceStatus status = getAttendanceStatus(meeting.getId(), avatar.getId());
        boolean isProof = isApprovedProof(meeting.getId(), avatar.getId());
        
        return new MeetingAttendanceStatus(
                meeting.getMeetingNo(), 
                meeting.getStudyDate(), 
                status, 
                isProof
        );
    }

    private AttendanceStatus getAttendanceStatus(Long meetingId, Long avatarId) {
        return attendanceReader
                .findFirstByMeetingIdAndAvatarIdOrderByIdDesc(meetingId, avatarId)
                .map(Attendance::getStatus)
                .orElse(AttendanceStatus.ABSENT);
    }

    private boolean isApprovedProof(Long meetingId, Long avatarId) {
        return proofReader.findLastProof(avatarId, meetingId)
                .map(Proof::isApproved)
                .orElse(false);
    }
}
