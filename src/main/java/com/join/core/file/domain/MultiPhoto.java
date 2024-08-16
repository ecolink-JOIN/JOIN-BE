package com.join.core.file.domain;

public interface MultiPhoto {

	ImageFile getFile();

	default String getKey() {
		return getFile().getS3Key();
	}

}
