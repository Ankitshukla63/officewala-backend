package com.officewala.dto.deadline;

import jakarta.validation.constraints.NotBlank;

public class CreateDeadlineNoteRequest {
    @NotBlank
    private String title;
    private String note;
    private String funnyLine;
    private String dueDate; // ISO date string

    public CreateDeadlineNoteRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getFunnyLine() { return funnyLine; }
    public void setFunnyLine(String funnyLine) { this.funnyLine = funnyLine; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
}
