package com.noyex.fileservice.service;

import com.noyex.fileservice.entity.FileEntity;
import com.noyex.fileservice.repository.FileRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    @Value("${file.storage.location}")
    private String storageLocation;
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Long uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        try{
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path FilePath = Paths.get(storageLocation + fileName);
            Files.createDirectories(FilePath.getParent());
            Files.write(FilePath, file.getBytes());

            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setFileName(fileName);
            fileEntity.setFilePath(FilePath.toString());
            FileEntity savedFile = fileRepository.save(fileEntity);

            return savedFile.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @SneakyThrows
    @Override
    public Resource getFile(Long fileId) {
        FileEntity fileEntity = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
        Path filePath = Paths.get(fileEntity.getFilePath());
        Resource resource = new UrlResource(filePath.toUri());
        if(!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("File not found");
        }
        return resource;
    }

    @Override
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}
