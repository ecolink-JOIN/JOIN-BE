package com.join.core.application.repository;

import com.join.core.application.constant.ApplicationStatus;
import com.join.core.application.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ApplicationReaderImpl implements ApplicationReader {

    private final ApplicationRepository applicationRepository;

    @Override
    public List<Application> getApproveApplications(Long studyId) {
        return applicationRepository.findByStudyIdAndStatus(studyId, ApplicationStatus.APPROVED);
    }
}
