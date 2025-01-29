package com.join.core.report.domain;

import com.join.core.avatar.domain.AvatarReader;
import com.join.core.report.dto.request.ReportRequest;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final StudyReader studyReader;
    private final AvatarReader avatarReader;
    private final ReportStore reportStore;

    @Transactional
    public void createReport(Long reporterId, Long studyId, ReportRequest reportRequest) {
        Study study = studyReader.getStudyById(studyId);
        Avatar reporter = avatarReader.getAvatarById(reporterId);
        Avatar writer = study.getWriter();

        reportStore.store(reportRequest, study, writer, reporter);
    }

}
