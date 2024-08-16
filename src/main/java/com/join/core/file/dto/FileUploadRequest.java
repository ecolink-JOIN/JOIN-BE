package com.join.core.file.dto;

import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest(
    MultipartFile file,
    String key
) {
}
