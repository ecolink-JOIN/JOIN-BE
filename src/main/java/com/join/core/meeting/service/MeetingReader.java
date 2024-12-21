package com.join.core.meeting.service;

import com.join.core.meeting.domain.Meeting;
import com.join.core.study.domain.Study;

import java.util.List;

public interface MeetingReader {
    List<Meeting> findByStudy(Study study);
    Meeting findByMeetingNo(int meetingNo);
}
