package com.join.core.study.service;

import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.StudyRuleResponse;
import com.join.core.study.mapper.StudyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyRuleService {

    private final StudyReader studyReader;
    private final StudyMapper studyMapper;

    @Transactional(readOnly = true)
    public StudyRuleResponse getRules(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        return StudyRuleResponse.of(study, studyMapper.toStudySchedulesDto(study), studyMapper.toFineReasonAmountsDto(study));
    }

}
