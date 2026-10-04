package com.officewala.repository;

import com.officewala.model.DeadlineNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeadlineNoteRepository extends JpaRepository<DeadlineNote, String> {
    List<DeadlineNote> findByUserIdOrderByCreatedAtDesc(String userId);
}
