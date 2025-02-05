package com.join.core.meeting.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.meeting.domain.Meeting;

import java.util.List;

public interface MeetingQueryRepository {

    List<Meeting> findMeetingsByAvatarIdAndEnrollmentStatuses(Long avatarId, List<EnrollmentStatus> statuses);
    List<Meeting> findMeetingsByStudyId(Long studyId);
}
