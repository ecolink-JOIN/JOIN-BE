package com.join.core.meeting.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.service.MeetingReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MeetingReaderImpl implements MeetingReader {

    private final MeetingRepository meetingRepository;

    @Override
    public List<Meeting> findByStudy(Study study) {
        return meetingRepository.findByStudy(study);
    }

    @Override
    public Meeting findByStudyIdAndMeetingNo(Long studyId, int meetingNo) {
        return meetingRepository.findByStudyIdAndMeetingNo(studyId, meetingNo)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEETING_NOT_FOUND));
    }
}
