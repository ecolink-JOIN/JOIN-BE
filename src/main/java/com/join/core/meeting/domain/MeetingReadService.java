package com.join.core.meeting.domain;

import com.join.core.meeting.dto.response.MeetingResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingReadService {

    private final StudyReader studyReader;
    private final MeetingReader meetingReader;

    @Transactional(readOnly = true)
    public List<MeetingResponse> getMeetings(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);

        return meetingReader.getMeetingsByStudy(study)
                .stream()
                .map(MeetingResponse::from)
                .toList();
    }

}
