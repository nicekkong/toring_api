package com.cplanet.toring.controller;

import com.cplanet.toring.dto.FileUploadResponseDto;
import com.cplanet.toring.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/file")
public class FileUploadController {
    private S3Service s3Service;

    public FileUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public FileUploadResponseDto uploadFile(MultipartFile file) {
        FileUploadResponseDto response = new FileUploadResponseDto();
        try {
            String filePath = s3Service.upload(file);
            if(!StringUtils.isEmpty(filePath)) {
                response.setSuccess(true);
                response.setName(file.getOriginalFilename());
                response.setType(file.getContentType());
                response.setPath(filePath);
            } else {
                response.setSuccess(false);
            }
        } catch (IOException e) {
            log.error("file upload error", e);
            response.setSuccess(false);
        }
        return response;
    }

}
