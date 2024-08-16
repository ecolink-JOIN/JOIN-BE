package com.join.core.file.service;

import java.util.List;

import com.join.core.file.dto.FileInfo;
import com.join.core.file.dto.FileUploadRequest;

public interface FileStorageService {

	FileInfo uploadFile(FileUploadRequest request);

	List<FileInfo> uploadFiles(List<FileUploadRequest> requests);

	void deleteFile(String key);

	void deleteFiles(List<String> keys);

	void recoverBackups(List<String> backupKeys);

	void deleteBackups(List<String> backupKeys);

}
