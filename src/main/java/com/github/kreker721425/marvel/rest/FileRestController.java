package com.github.kreker721425.marvel.rest;

import com.github.kreker721425.marvel.domain.UploadInfo;
import com.github.kreker721425.marvel.dto.UploadResponseDto;
import com.github.kreker721425.marvel.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileRestController {

    private final FileService fileService;


    @PostMapping("/upload")
    public UploadResponseDto upload(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> get(@PathVariable String id) {
        UploadInfo uploadInfo = fileService.get(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadInfo.getMimeType()))
                .body(uploadInfo.getResource());
    }
}
