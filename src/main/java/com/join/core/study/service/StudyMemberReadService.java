package com.join.core.study.service;

import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.StudyMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.join.core.enrollment.constant.EnrollmentStatus.*;

@Service
@RequiredArgsConstructor
public class StudyMemberReadService {

    private final EnrollmentReader enrollmentReader;
    private final StudyReader studyReader;

    @Transactional(readOnly = true)
    public Collection<StudyMemberResponse> getStudyMembers(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);

        return enrollmentReader.getByStudyId(study.getId()).stream()
                .filter(enrollment -> enrollment.getStatus() == JOINED)
                .map(enrollment -> StudyMemberResponse.of(enrollment.getAvatar(), enrollment.getRole().name()))
                .toList();
    }

}
