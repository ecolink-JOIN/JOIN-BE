package com.join.core.application.repository;

import com.join.core.application.domain.Application;

import java.util.List;

public interface ApplicationReader {

    List<Application> getApproveApplications(Long studyId);
}
