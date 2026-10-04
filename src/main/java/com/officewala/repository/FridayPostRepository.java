package com.officewala.repository;

import com.officewala.model.FridayPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridayPostRepository extends JpaRepository<FridayPost, String> {
    Page<FridayPost> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);
}
