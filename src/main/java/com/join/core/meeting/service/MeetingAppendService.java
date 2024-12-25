package com.join.core.meeting.service;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.meeting.domain.Meeting;
import com.join.core.meeting.dto.request.MeetingAppendRequest;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MeetingAppendService {

    private final MeetingStore meetingStore;
    private final StudyReader studyReader;
    private final MeetingReader meetingReader;

    @Transactional
    public void appendMeetingToStudy(Long avatarId, String studyToken, MeetingAppendRequest request) {

        Study study = studyReader.getStudyByToken(studyToken);

        if (!study.getWriter().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }
        Meeting newMeeting = request.toEntity(study);

        meetingStore.store(newMeeting);

        List<Meeting> meetings = meetingReader.getMeetingsByStudy(study);
        int meetingCount = meetings.size();

        meetings.sort(Comparator.comparing(meeting ->
                LocalDateTime.of(meeting.getStudyDate(), meeting.getStTime())));

        IntStream.range(0, meetingCount).forEach(index -> {
            Meeting meeting = meetings.get(index);
            meeting.updateMeetingNo(index + 1);
        });

    }

}
