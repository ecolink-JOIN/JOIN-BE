package com.join.core.study.service;

import com.join.core.study.domain.Study;

public interface StudyReader {
    Study getStudyByToken(String studyToken);

}
