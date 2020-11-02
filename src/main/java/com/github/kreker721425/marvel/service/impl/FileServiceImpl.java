package com.github.kreker721425.marvel.service.impl;

import com.github.kreker721425.marvel.exception.FileIsEmptyException;
import com.github.kreker721425.marvel.exception.FileStorageException;
import com.github.kreker721425.marvel.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileIsEmptyException();
        }
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        try {
            file.transferTo(Paths.get(uploadPath).resolve(fileName));
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException();
        }
    }
}
