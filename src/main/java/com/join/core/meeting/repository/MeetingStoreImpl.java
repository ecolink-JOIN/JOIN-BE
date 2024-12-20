package com.join.core.meeting.repository;

import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.service.MeetingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MeetingStoreImpl implements MeetingStore {

    private final MeetingRepository meetingRepository;

    @Override
    public void store(Meeting meeting) {
        meetingRepository.save(meeting);
    }

}
