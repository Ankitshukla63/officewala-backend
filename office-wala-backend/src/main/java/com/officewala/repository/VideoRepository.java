package com.officewala.repository;

import com.officewala.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {
    List<Video> findByUserIdOrderByCreatedAtDesc(String userId);
    List<Video> findByCategoryAndUserIdOrderByCreatedAtDesc(String category, String userId);
    List<Video> findByUserIdAndVideoUrlIsNotNull(String userId);
    
    @Query(value = "SELECT * FROM videos WHERE user_id = :userId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Video findRandomVideoByUserId(@Param("userId") String userId);
}
