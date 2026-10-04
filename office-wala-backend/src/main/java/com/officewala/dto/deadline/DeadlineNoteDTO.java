package com.officewala.dto.deadline;

import com.officewala.model.DeadlineNote;

public class DeadlineNoteDTO {
    private String id;
    private String title;
    private String note;
    private String funnyLine;
    private String dueDate;
    private String userId;
    private String authorName;
    private String createdAt;

    public DeadlineNoteDTO() {}

    public static DeadlineNoteDTO from(DeadlineNote d) {
        if (d == null) return null;
        DeadlineNoteDTO dto = new DeadlineNoteDTO();
        dto.setId(d.getId());
        dto.setTitle(d.getTitle());
        dto.setNote(d.getNote());
        dto.setFunnyLine(d.getFunnyLine());
        dto.setDueDate(d.getDueDate() != null ? d.getDueDate().toString() : null);
        dto.setUserId(d.getUserId());
        dto.setAuthorName(d.getAuthorName());
        dto.setCreatedAt(d.getCreatedAt() != null ? d.getCreatedAt().toString() : null);
        return dto;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getFunnyLine() { return funnyLine; }
    public void setFunnyLine(String funnyLine) { this.funnyLine = funnyLine; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
