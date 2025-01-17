package com.join.core.proof.repository.photo;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.proof.domain.ProofPhoto;
import com.join.core.proof.service.ProofPhotoReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProofPhotoReaderImpl implements ProofPhotoReader {

    private final ProofPhotoRepository proofPhotoRepository;

    @Override
    public ProofPhoto readPhoto(String path) {
        return proofPhotoRepository.findByFileUrl(path)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROOF_PHOTO_NOT_FOUND));
    }
}
