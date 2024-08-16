package com.join.core.file.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.FileUploadException;
import com.join.core.file.dto.ImageSize;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageFileService {

	public ImageSize getSize(MultipartFile image) {
		try (InputStream inputStream = image.getInputStream()) {
			BufferedImage read = ImageIO.read(inputStream);
			int width = read.getWidth();
			int height = read.getHeight();
			Long size = image.getSize();
			return new ImageSize(size, width, height);
		} catch (IOException e) {
			log.warn(e.toString());
			throw new FileUploadException(ErrorCode.FAIL_TO_ANALYZE_FILE);
		}
	}

}
