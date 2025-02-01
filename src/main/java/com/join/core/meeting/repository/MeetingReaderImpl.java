package com.join.core.meeting.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class MeetingReaderImpl implements MeetingReader {

    private final MeetingRepository meetingRepository;
    private final MeetingQueryRepository meetingQueryRepository;

    @Override
    public List<Meeting> getMeetingsByStudy(Study study) {
        return meetingRepository.findByStudy(study);
    }

    @Override
    public Meeting getMeetingById(Long meetingId) {
        return meetingRepository.findById(meetingId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEETING_NOT_FOUND));
    }

    @Override
    public Meeting findByStudyIdAndMeetingNo(Long studyId, int meetingNo) {
        return meetingRepository.findByStudyIdAndMeetingNo(studyId, meetingNo)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEETING_NOT_FOUND));
    }

    @Override
    public List<Meeting> findMeetingsByAvatarIdForStudies(Long avatarId) {
        List<Meeting> meetingsForJoinedStudies = meetingQueryRepository.findMeetingsByAvatarIdAndEnrollmentStatuses(avatarId, List.of(EnrollmentStatus.JOINED, EnrollmentStatus.REQUEST_LEAVE));
        List<Meeting> meetingsForLeftStudies = meetingQueryRepository.findMeetingsByAvatarIdAndEnrollmentStatuses(avatarId, List.of(EnrollmentStatus.LEFT));

        return Stream.concat(meetingsForJoinedStudies.stream(), meetingsForLeftStudies.stream()).toList();
    }

    @Override
    public List<Meeting> findMeetingsByStudyId(Long studyId) {
        return meetingQueryRepository.findMeetingsByStudyId(studyId);
    }
}
