package com.officewala.controller;

import com.officewala.dto.ApiResponse;
import com.officewala.model.Profile;
import com.officewala.security.SecurityUtils;
import com.officewala.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Profile>> getMyProfile() {
        String userId = SecurityUtils.getCurrentUserId();
        Profile profile = profileService.getByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok(profile));
    }
}
