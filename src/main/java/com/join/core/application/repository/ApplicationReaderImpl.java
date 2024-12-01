package com.join.core.application.repository;

import com.join.core.application.service.ApplicationReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationReaderImpl implements ApplicationReader {

    private final ApplicationQueryRepository applicationQueryRepository;

    @Override
    public double getAverageByStudyId(Long studyId) {
        return applicationQueryRepository.getAverageByStudyId(studyId);
    }
}
