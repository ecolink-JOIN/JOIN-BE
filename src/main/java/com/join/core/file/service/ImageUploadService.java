package com.join.core.file.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.join.core.file.constant.FilePath;
import com.join.core.file.domain.ImageFile;
import com.join.core.file.dto.FileInfo;
import com.join.core.file.dto.FileUploadRequest;
import com.join.core.file.dto.ImageSize;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageUploadService {

	private final FileStorageService fileStorageService;
	private final FilePathStrategy filePathStrategy;
	private final ImageFileService imageFileService;
	private final FileTypeService fileTypeService;

	public List<ImageFile> uploadImages(List<MultipartFile> images, FilePath path, Object id) {
		List<FileUploadRequest> fileUploadRequests = images.stream()
			.map(image -> new FileUploadRequest(image, getKey(image, path, id)))
			.toList();
		return fileStorageService.uploadFiles(fileUploadRequests).stream().map(this::convert).toList();
	}

	public ImageFile uploadImage(MultipartFile image, FilePath path, Object id) {
		fileTypeService.validateImageType(image);
		FileUploadRequest fileUploadRequest = new FileUploadRequest(image, getKey(image, path, id));
		return convert(fileStorageService.uploadFile(fileUploadRequest));
	}

	private String getKey(MultipartFile image, FilePath path, Object id) {
		return filePathStrategy.getPathOf(image, path, id);
	}

	private ImageFile convert(FileInfo info) {
		ImageSize imageSize = imageFileService.getSize(info.file());
		return ImageFile.of(info, imageSize);
	}

	public void removeFiles(List<String> keys) {
		fileStorageService.deleteFiles(keys);
	}

	public void removeFile(String key) {
		fileStorageService.deleteFile(key);
	}

}
