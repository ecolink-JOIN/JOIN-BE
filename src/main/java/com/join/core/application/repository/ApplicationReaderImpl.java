package com.join.core.application.repository;

import com.join.core.application.domain.Application;
import com.join.core.application.domain.ApplicationReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationReaderImpl implements ApplicationReader {

    private final ApplicationRepository applicationRepository;
    private final StudyReader studyReader;

    @Override
    @Transactional(readOnly = true)
    public List<Application> getApplicationsByStudyToken(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        return applicationRepository.findByStudy(study);
    }
}
