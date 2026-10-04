package com.officewala.dto.friday;

import jakarta.validation.constraints.NotBlank;

public class CreateFridayPostRequest {
    @NotBlank
    private String imageUrl;
    private String caption;

    public CreateFridayPostRequest() {}

    public CreateFridayPostRequest(String imageUrl, String caption) {
        this.imageUrl = imageUrl;
        this.caption = caption;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
}
