package com.github.kreker721425.marvel.service;

import com.github.kreker721425.marvel.domain.UploadInfo;
import com.github.kreker721425.marvel.dto.UploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    UploadResponseDto uploadFile(MultipartFile file);

    UploadInfo get(String id);
}
