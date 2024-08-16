package com.join.core.file.domain;

public interface SinglePhoto {

	ImageFile getFile();

	default String getKey() {
		return getFile().getS3Key();
	}

	default boolean isUploadedImage() {
		return getKey() != null;
	}

}
