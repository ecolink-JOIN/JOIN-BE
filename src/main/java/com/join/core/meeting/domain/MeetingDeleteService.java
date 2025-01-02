package com.join.core.meeting.domain;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MeetingDeleteService {

    private final MeetingReader meetingReader;
    private final StudyReader studyReader;
    private final MeetingDeleter meetingDeleter;

    @Transactional
    public void delete(Long avatarId, String studyToken, Long meetingId) {

        Study study = studyReader.getStudyByToken(studyToken);

        if (!study.isWriter(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        Meeting meetingToDelete = meetingReader.getMeetingById(meetingId);

        meetingDeleter.delete(meetingToDelete);

        List<Meeting> remainingMeetings = meetingReader.getMeetingsByStudy(study)
                .stream()
                .filter(meeting -> !meeting.getId().equals(meetingId))
                .sorted(Comparator.comparing(meeting ->
                        LocalDateTime.of(meeting.getStudyDate(), meeting.getStTime())))
                .toList();

        IntStream.range(0, remainingMeetings.size()).forEach(index -> {
            Meeting meeting = remainingMeetings.get(index);
            meeting.updateMeetingNo(index + 1);
        });
    }

}
