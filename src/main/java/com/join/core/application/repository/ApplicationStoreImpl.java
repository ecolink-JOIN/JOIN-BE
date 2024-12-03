package com.join.core.application.repository;

import com.join.core.application.domain.Application;
import com.join.core.application.service.ApplicationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationStoreImpl implements ApplicationStore {

    private final ApplicationRepository applicationRepository;

    @Override
    public void store(Application application) {
        applicationRepository.save(application);
    }

}
