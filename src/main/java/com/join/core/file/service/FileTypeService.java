package com.join.core.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.FileUploadException;

@Service
public class FileTypeService {

	private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png", "gif", "bmp", "wbmp");

	public String getMIMEType(MultipartFile multipartFile) {
		File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		String mimeType;
		try {
			mimeType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			throw new FileUploadException(ErrorCode.FAIL_TO_GET_MIME_TYPE_OF_FILE);
		}
		return mimeType;
	}

	public String getExtension(MultipartFile file) {
		return getExtension(getMIMEType(file));
	}

	public String getExtension(String mimeType) {
		return mimeType.split("/")[1];
	}

	public void validateImageType(MultipartFile file) {
		String mimeType = getMIMEType(file);
		if (!mimeType.startsWith("image"))
			throw new FileUploadException(ErrorCode.NOT_IMAGE_FILE);

		String extension = getExtension(mimeType);
		if (!ALLOWED_EXTENSIONS.contains(extension))
			throw new FileUploadException(ErrorCode.IMAGE_EXTENSION_IS_NOT_ALLOWED);

	}

}
