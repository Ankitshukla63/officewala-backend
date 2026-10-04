package com.officewala.dto.video;

import com.officewala.model.Video;

public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private String notes;
    private String videoUrl;
    private String thumbnailUrl;
    private String imageUrl;
    private String category;
    private String clientName;
    private String eventDate;
    private String startTime;
    private String endTime;
    private String userId;
    private String authorName;
    private String createdAt;

    public VideoDTO() {}

    public static VideoDTO from(Video v) {
        if (v == null) return null;
        VideoDTO dto = new VideoDTO();
        dto.setId(v.getId());
        dto.setTitle(v.getTitle());
        dto.setDescription(v.getDescription());
        dto.setNotes(v.getNotes());
        dto.setVideoUrl(v.getVideoUrl());
        dto.setThumbnailUrl(v.getThumbnailUrl());
        dto.setImageUrl(v.getImageUrl());
        dto.setCategory(v.getCategory());
        dto.setClientName(v.getClientName());
        dto.setEventDate(v.getEventDate() != null ? v.getEventDate().toString() : null);
        dto.setStartTime(v.getStartTime());
        dto.setEndTime(v.getEndTime());
        dto.setUserId(v.getUserId());
        dto.setAuthorName(v.getAuthorName());
        dto.setCreatedAt(v.getCreatedAt() != null ? v.getCreatedAt().toString() : null);
        return dto;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
