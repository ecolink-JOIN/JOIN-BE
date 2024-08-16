package com.join.core.file.dto;

import org.springframework.web.multipart.MultipartFile;

public record FileInfo(
	MultipartFile file,
	String url,
	String key
) {
}
