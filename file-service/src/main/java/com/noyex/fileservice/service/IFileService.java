package com.noyex.fileservice.service;

import com.noyex.fileservice.entity.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    Long uploadFile(MultipartFile file);
    Resource getFile(Long fileId);
    List<FileEntity> getAllFiles();
    void deleteFile(Long fileId);
}
