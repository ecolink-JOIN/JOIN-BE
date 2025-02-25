package com.join.core.job.repository;

import com.join.core.job.domain.BatchJob;
import com.join.core.job.domain.BatchJobStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BatchJobStoreImpl implements BatchJobStore {

    private final BatchJobRepository batchJobRepository;

    @Override
    public BatchJob store(BatchJob batchJob) {
        return batchJobRepository.save(batchJob);
    }

}
