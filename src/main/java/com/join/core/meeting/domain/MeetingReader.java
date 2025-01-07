package com.join.core.meeting.domain;

import com.join.core.study.domain.Study;

import java.util.List;

public interface MeetingReader {
    List<Meeting> getMeetingsByStudy(Study study);
    Meeting getMeetingById(Long meetingId);
    Meeting findByStudyIdAndMeetingNo(Long studyId, int meetingNo);
}
