package com.officewala.controller;

import com.officewala.dto.ApiResponse;
import com.officewala.dto.deadline.CreateDeadlineNoteRequest;
import com.officewala.dto.deadline.DeadlineNoteDTO;
import com.officewala.model.DeadlineNote;
import com.officewala.model.Profile;
import com.officewala.security.SecurityUtils;
import com.officewala.service.DeadlineService;
import com.officewala.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deadline-notes")
public class DeadlineController {

    private final DeadlineService deadlineService;
    private final ProfileService profileService;

    public DeadlineController(DeadlineService deadlineService, ProfileService profileService) {
        this.deadlineService = deadlineService;
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DeadlineNoteDTO>>> getNotes() {
        String userId = SecurityUtils.getCurrentUserId();
        List<DeadlineNoteDTO> notes = deadlineService.getNotes(userId).stream()
                .map(DeadlineNoteDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(notes));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DeadlineNoteDTO>> createNote(@Valid @RequestBody CreateDeadlineNoteRequest request) {
        String userId = SecurityUtils.getCurrentUserId();
        Profile profile = profileService.getByUserId(userId);
        DeadlineNote note = deadlineService.createNote(request, userId, profile.getDisplayName());
        return ResponseEntity.ok(ApiResponse.ok(DeadlineNoteDTO.from(note)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNote(@PathVariable String id) {
        String userId = SecurityUtils.getCurrentUserId();
        deadlineService.deleteNote(id, userId);
        return ResponseEntity.ok(ApiResponse.ok("Note deleted successfully"));
    }
}
