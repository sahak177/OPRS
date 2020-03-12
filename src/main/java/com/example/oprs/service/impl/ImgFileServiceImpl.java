package com.example.oprs.service.impl;

import com.example.oprs.exception.FileStorageException;
import com.example.oprs.service.ImgFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImgFileServiceImpl implements ImgFileService {

    @Value("target\\classes\\static\\img\\")
    public String uploadDir;

    @Value("src\\main\\resources\\static\\img\\")
    public String uploadDirDuplicate;

    @Override
    public String uploadFile(MultipartFile file, String name) {
        try {
            Path copyLocation = Paths.get(uploadDir + name + ".png");
            Path copyLocationDuplicate = Paths.get(uploadDirDuplicate + name + ".png");
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file.getInputStream(), copyLocationDuplicate, StandardCopyOption.REPLACE_EXISTING);
            return "/img/" + name + ".png";
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }

    public String uploadFile2(MultipartFile file, String name) {
        try {
            String copyLocation = Paths.get(uploadDir + name + ".png").toString();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(copyLocation)));
            stream.write(file.getBytes());
            stream.close();
            return "/img/" + name + ".png";
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }

    private String getURLFileFormat(String path) {
        String[] result = path.split("\\.(?=[^.]+$)");
        return result[result.length - 1];
    }
}