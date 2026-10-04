package com.officewala.controller;

import com.officewala.dto.ApiResponse;
import com.officewala.dto.video.CreateVideoRequest;
import com.officewala.dto.video.VideoDTO;
import com.officewala.model.Profile;
import com.officewala.model.Video;
import com.officewala.security.SecurityUtils;
import com.officewala.service.ProfileService;
import com.officewala.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;
    private final ProfileService profileService;

    public VideoController(VideoService videoService, ProfileService profileService) {
        this.videoService = videoService;
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VideoDTO>>> getVideos(@RequestParam(required = false) String category) {
        String userId = SecurityUtils.getCurrentUserId();
        List<VideoDTO> videos = videoService.getVideos(userId, category).stream()
                .map(VideoDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(videos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VideoDTO>> createVideo(@Valid @RequestBody CreateVideoRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        Profile profile = profileService.getByUserId(userId);
        Video video = videoService.createVideo(request, userId, profile.getDisplayName());
        return ResponseEntity.ok(ApiResponse.ok(VideoDTO.from(video)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVideo(@PathVariable String id) {
        String userId = SecurityUtils.getCurrentUserId();
        videoService.deleteVideo(id, userId);
        return ResponseEntity.ok(ApiResponse.ok("Video deleted successfully"));
    }

    @GetMapping("/random")
    public ResponseEntity<ApiResponse<VideoDTO>> getRandomVideo() {
        String userId = SecurityUtils.getCurrentUserId();
        Video video = videoService.getRandomVideo(userId);
        return ResponseEntity.ok(ApiResponse.ok(VideoDTO.from(video)));
    }
}
