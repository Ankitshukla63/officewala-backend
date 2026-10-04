package com.officewala.service;

import com.officewala.dto.deadline.CreateDeadlineNoteRequest;
import com.officewala.model.DeadlineNote;
import com.officewala.repository.DeadlineNoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeadlineService {

    private final DeadlineNoteRepository deadlineNoteRepository;

    public DeadlineService(DeadlineNoteRepository deadlineNoteRepository) {
        this.deadlineNoteRepository = deadlineNoteRepository;
    }

    public List<DeadlineNote> getNotes(String userId) {
        return deadlineNoteRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public DeadlineNote createNote(CreateDeadlineNoteRequest request, String userId, String authorName) {
        DeadlineNote note = new DeadlineNote();
        note.setTitle(request.getTitle());
        note.setNote(request.getNote());
        note.setFunnyLine(request.getFunnyLine());
        
        if (request.getDueDate() != null && !request.getDueDate().isEmpty()) {
            note.setDueDate(LocalDate.parse(request.getDueDate()));
        }
        
        note.setUserId(userId);
        note.setAuthorName(authorName);
        
        return deadlineNoteRepository.save(note);
    }

    public void deleteNote(String id, String userId) {
        DeadlineNote note = deadlineNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
                
        if (!note.getUserId().equals(userId)) {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized to delete this note");
        }
        
        deadlineNoteRepository.delete(note);
    }
}
