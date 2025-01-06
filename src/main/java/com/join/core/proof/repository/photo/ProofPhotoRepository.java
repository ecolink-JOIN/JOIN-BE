package com.join.core.proof.repository.photo;

import com.join.core.proof.domain.ProofPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProofPhotoRepository extends JpaRepository<ProofPhoto, Long> {

    Optional<ProofPhoto> findByFileUrl(String fileUrl);
}
