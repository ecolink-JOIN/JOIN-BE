package com.join.core.study.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class StudyReaderImpl implements StudyReader {

    private final StudyRepository studyRepository;
    private final StudyQueryRepository studyQueryRepository;

    @Override
    public Study getStudyByToken(String studyToken) {
        return studyRepository.findByStudyToken(studyToken)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STUDY_NOT_FOUND));
    }

    @Override
    public Study getStudyById(Long studyId) {
        return studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STUDY_NOT_FOUND));
    }

    @Override
    public Page<Study> getStudyOrderByPopularity(Long categoryId, StudyForm form, LocalDateTime now, Pageable pageable) {
        return studyQueryRepository.getStudiesOrderByPopularity(
                categoryId,
                form,
                now,
                pageable
        );
    }

}
