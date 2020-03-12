package com.example.oprs.service;

import com.example.oprs.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;

public interface ImgFileService {
    String uploadFile(MultipartFile file, String name) throws FileStorageException;
}