package com.officewala.controller;

import com.officewala.dto.ApiResponse;
import com.officewala.security.SecurityUtils;
import com.officewala.service.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "bucket", defaultValue = "general") String bucket) {
        
        String userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            userId = "anonymous";
        }
        
        String publicUrl = fileStorageService.store(file, bucket, userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", publicUrl));
    }
}
