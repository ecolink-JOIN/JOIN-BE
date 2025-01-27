package com.join.core.proof.repository;

import com.join.core.proof.domain.Proof;
import com.join.core.proof.service.ProofStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProofStoreImpl implements ProofStore {

    private final ProofRepository proofRepository;

    @Override
    public Proof save(Proof proof) {
        return proofRepository.save(proof);
    }
}
