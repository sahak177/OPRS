package com.example.oprs.service;

import com.example.oprs.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file, String name) throws FileStorageException;
}