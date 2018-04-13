package com.jingxi.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
	@SuppressWarnings("rawtypes")
	public Map uploadFile(MultipartFile uploadFile) throws Exception;
}
