package com.join.core.meeting.service;

import com.join.core.meeting.domain.Meeting;
import com.join.core.study.domain.Study;

import java.util.List;

public interface MeetingReader {
    List<Meeting> getMeetingsByStudy(Study study);
    Meeting getMeetingById(Long meetingId);

}
