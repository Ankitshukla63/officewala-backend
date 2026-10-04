package com.officewala.dto.friday;

import com.officewala.model.FridayPost;

public class FridayPostDTO {
    private String id;
    private String imageUrl;
    private String caption;
    private String userName;
    private String userId;
    private String createdAt;

    public FridayPostDTO() {}

    public static FridayPostDTO from(FridayPost f) {
        if (f == null) return null;
        FridayPostDTO dto = new FridayPostDTO();
        dto.setId(f.getId());
        dto.setImageUrl(f.getImageUrl());
        dto.setCaption(f.getCaption());
        dto.setUserName(f.getUserName());
        dto.setUserId(f.getUserId());
        dto.setCreatedAt(f.getCreatedAt() != null ? f.getCreatedAt().toString() : null);
        return dto;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
