package com.officewala.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir:./uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        java.nio.file.Path uploadDirPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            if (!java.nio.file.Files.exists(uploadDirPath)) {
                java.nio.file.Files.createDirectories(uploadDirPath);
            }
        } catch (java.io.IOException ignored) {
        }
        String uploadPath = uploadDirPath.toUri().toString();
        if (!uploadPath.endsWith("/")) {
            uploadPath += "/";
        }
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
    }
}
