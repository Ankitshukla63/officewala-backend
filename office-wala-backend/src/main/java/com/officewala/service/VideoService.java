package com.officewala.service;

import com.officewala.dto.video.CreateVideoRequest;
import com.officewala.model.Video;
import com.officewala.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getVideos(String userId, String category) {
        if (category != null && !category.isEmpty()) {
            return videoRepository.findByCategoryAndUserIdOrderByCreatedAtDesc(category, userId);
        }
        return videoRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Video createVideo(CreateVideoRequest request, String userId, String authorName) {
        Video video = new Video();
        video.setTitle(request.getTitle());
        video.setDescription(request.getDescription());
        video.setNotes(request.getNotes());
        video.setVideoUrl(request.getVideoUrl());
        video.setThumbnailUrl(request.getThumbnailUrl());
        video.setImageUrl(request.getImageUrl());
        video.setCategory(request.getCategory());
        video.setClientName(request.getClientName());
        
        if (request.getEventDate() != null && !request.getEventDate().isEmpty()) {
            video.setEventDate(LocalDate.parse(request.getEventDate()));
        }
        
        video.setStartTime(request.getStartTime());
        video.setEndTime(request.getEndTime());
        video.setUserId(userId);
        video.setAuthorName(authorName);

        return videoRepository.save(video);
    }

    public void deleteVideo(String id, String userId) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        
        if (!video.getUserId().equals(userId)) {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized to delete this video");
        }
        
        videoRepository.delete(video);
    }

    public Video getRandomVideo(String userId) {
        return videoRepository.findRandomVideoByUserId(userId);
    }
}
