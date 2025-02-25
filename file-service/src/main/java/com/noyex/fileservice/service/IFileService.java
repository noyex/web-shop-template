package com.noyex.fileservice.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    Long uploadFile(MultipartFile file);
    Resource getFile(Long fileId);
}
