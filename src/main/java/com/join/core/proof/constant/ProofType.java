package com.join.core.proof.constant;

public enum ProofType {
    PHOTO, TIMER;

    public boolean isPhotoType() {
        return this == PHOTO;
    }
}
