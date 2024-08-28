package com.join.core.study.service;

import com.join.core.study.domain.Study;
import com.join.core.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    @Transactional
    public void saveStudy(Study study) {
        studyRepository.save(study);
    }

}
