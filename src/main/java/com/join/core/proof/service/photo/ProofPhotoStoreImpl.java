package com.join.core.proof.service.photo;

import com.join.core.proof.domain.ProofPhoto;
import com.join.core.proof.repository.photo.ProofPhotoRepository;
import com.join.core.proof.repository.photo.ProofPhotoStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProofPhotoStoreImpl implements ProofPhotoStore {

    private final ProofPhotoRepository proofPhotoRepository;

    public ProofPhoto save(ProofPhoto proofPhoto) {
        return proofPhotoRepository.save(proofPhoto);
    }
}