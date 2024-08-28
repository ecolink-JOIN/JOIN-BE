package com.join.core.study.repository;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import com.join.core.study.service.StudyStore;
import com.join.core.study.domain.Study;

@RequiredArgsConstructor
@Component
public class StudyStoreImpl implements StudyStore {

    private final StudyRepository studyRepository;

    @Override
    public Study store(Study study) {
        return studyRepository.save(study);
    }

}
