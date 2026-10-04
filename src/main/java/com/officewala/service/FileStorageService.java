package com.officewala.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir:./uploads}")
    private String baseUploadDir;

    public String store(MultipartFile file, String bucket, String userId) {
        try {
            String originalFilename = file.getOriginalFilename();
            String ext = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String newFilename = UUID.randomUUID().toString() + ext;
            Path userDir = Paths.get(baseUploadDir, bucket, userId);
            
            if (!Files.exists(userDir)) {
                Files.createDirectories(userDir);
            }

            Path targetLocation = userDir.resolve(newFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + bucket + "/" + userId + "/" + newFilename;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
        }
    }

    public void delete(String path) {
        try {
            if (path != null && path.startsWith("/uploads/")) {
                String relativePath = path.substring("/uploads/".length());
                Path file = Paths.get(baseUploadDir, relativePath);
                Files.deleteIfExists(file);
            }
        } catch (IOException ex) {
            // Ignore error or log it
        }
    }
}
