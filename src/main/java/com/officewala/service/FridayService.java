package com.officewala.service;

import com.officewala.dto.friday.CreateFridayPostRequest;
import com.officewala.dto.friday.FridayPostDTO;
import com.officewala.dto.friday.PagedFridayResponse;
import com.officewala.model.FridayPost;
import com.officewala.repository.FridayPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FridayService {

    private final FridayPostRepository fridayPostRepository;

    public FridayService(FridayPostRepository fridayPostRepository) {
        this.fridayPostRepository = fridayPostRepository;
    }

    public PagedFridayResponse getPosts(String userId, int page, int pageSize) {
        Page<FridayPost> postsPage = fridayPostRepository.findByUserIdOrderByCreatedAtDesc(
                userId, PageRequest.of(page - 1, pageSize)); // 0-based internal

        return new PagedFridayResponse(
                postsPage.getContent().stream().map(FridayPostDTO::from).collect(Collectors.toList()),
                postsPage.getTotalElements(),
                page,
                pageSize,
                postsPage.getTotalPages()
        );
    }

    public FridayPost createPost(CreateFridayPostRequest request, String userId, String userName) {
        FridayPost post = new FridayPost();
        post.setImageUrl(request.getImageUrl());
        post.setCaption(request.getCaption());
        post.setUserId(userId);
        post.setUserName(userName);
        return fridayPostRepository.save(post);
    }

    public void deletePost(String id, String userId) {
        FridayPost post = fridayPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
                
        if (!post.getUserId().equals(userId)) {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized to delete this post");
        }
        
        fridayPostRepository.delete(post);
    }
}
