package com.join.core.proof.service.photo;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.file.constant.FilePath;
import com.join.core.file.domain.ImageFile;
import com.join.core.file.service.ImageUploadService;
import com.join.core.proof.domain.ProofPhoto;
import com.join.core.proof.repository.photo.ProofPhotoStore;
import com.join.core.proof.dto.response.ProofPhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProofPhotoService {

    private final ImageUploadService imageUploadService;
    private final AvatarReader avatarReader;
    private final ProofPhotoStore proofPhotoStore;

    public ProofPhotoResponse savePhoto(MultipartFile file, Long avatarId) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        ImageFile imageFile = imageUploadService.uploadImage(file, FilePath.PROOF_PHOTO, avatar.getId());
        ProofPhoto proofPhoto = proofPhotoStore.save(new ProofPhoto(imageFile));
        return new ProofPhotoResponse(proofPhoto.getFile().getUrl());
    }
}
