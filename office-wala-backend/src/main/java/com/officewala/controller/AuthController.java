package com.officewala.controller;

import com.officewala.dto.ApiResponse;
import com.officewala.dto.auth.AuthResponse;
import com.officewala.dto.auth.SigninRequest;
import com.officewala.dto.auth.SignupRequest;
import com.officewala.model.Profile;
import com.officewala.security.SecurityUtils;
import com.officewala.service.AuthService;
import com.officewala.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final ProfileService profileService;

    public AuthController(AuthService authService, ProfileService profileService) {
        this.authService = authService;
        this.profileService = profileService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@Valid @RequestBody SignupRequest request) {
        AuthResponse response = authService.signup(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<AuthResponse>> signin(@Valid @RequestBody SigninRequest request) {
        AuthResponse response = authService.signin(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Profile>> me() {
        String userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new org.springframework.security.access.AccessDeniedException("Not authenticated");
        }
        Profile profile = profileService.getByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok(profile));
    }
}
