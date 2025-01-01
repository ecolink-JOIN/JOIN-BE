package com.join.core.meeting.repository;

import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.domain.MeetingDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MeetingDeleterImpl implements MeetingDeleter {

    private final MeetingRepository meetingRepository;

    @Override
    public void delete(Meeting meeting) {
        meetingRepository.delete(meeting);
    }
}
