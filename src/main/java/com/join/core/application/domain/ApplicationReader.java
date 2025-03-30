package com.join.core.application.domain;

import java.util.List;

public interface ApplicationReader {
    List<Application> getApplicationsByStudyToken(String studyToken);
}
