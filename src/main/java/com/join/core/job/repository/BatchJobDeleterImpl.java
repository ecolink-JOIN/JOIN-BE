package com.join.core.job.repository;

import com.join.core.job.domain.BatchJobDeleter;
import com.join.core.job.domain.BatchJob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BatchJobDeleterImpl implements BatchJobDeleter {

    private final BatchJobRepository batchJobRepository;

    @Override
    public void deleteBatchJob(BatchJob batchJob) {
        batchJobRepository.delete(batchJob);
    }

}
