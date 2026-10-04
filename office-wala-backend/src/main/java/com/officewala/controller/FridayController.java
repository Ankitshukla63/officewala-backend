package com.officewala.controller;

import com.officewala.dto.ApiResponse;
import com.officewala.dto.friday.CreateFridayPostRequest;
import com.officewala.dto.friday.FridayPostDTO;
import com.officewala.dto.friday.PagedFridayResponse;
import com.officewala.model.FridayPost;
import com.officewala.model.Profile;
import com.officewala.security.SecurityUtils;
import com.officewala.service.FridayService;
import com.officewala.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friday-posts")
public class FridayController {

    private final FridayService fridayService;
    private final ProfileService profileService;

    public FridayController(FridayService fridayService, ProfileService profileService) {
        this.fridayService = fridayService;
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedFridayResponse>> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int size) {
        String userId = SecurityUtils.getCurrentUserId();
        PagedFridayResponse response = fridayService.getPosts(userId, page, size);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FridayPostDTO>> createPost(@Valid @RequestBody CreateFridayPostRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        Profile profile = profileService.getByUserId(userId);
        FridayPost post = fridayService.createPost(request, userId, profile.getDisplayName());
        return ResponseEntity.ok(ApiResponse.ok(FridayPostDTO.from(post)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable String id) {
        String userId = SecurityUtils.getCurrentUserId();
        fridayService.deletePost(id, userId);
        return ResponseEntity.ok(ApiResponse.ok("Post deleted successfully"));
    }
}
