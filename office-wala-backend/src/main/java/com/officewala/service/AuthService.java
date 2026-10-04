package com.officewala.service;

import com.officewala.dto.auth.AuthResponse;
import com.officewala.dto.auth.SigninRequest;
import com.officewala.dto.auth.SignupRequest;
import com.officewala.model.Profile;
import com.officewala.model.User;
import com.officewala.repository.ProfileRepository;
import com.officewala.repository.UserRepository;
import com.officewala.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, ProfileRepository profileRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);

        Profile profile = new Profile();
        profile.setId(user.getId());
        if (request.getDisplayName() != null && !request.getDisplayName().trim().isEmpty()) {
            profile.setDisplayName(request.getDisplayName());
        }
        profile = profileRepository.save(profile);

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        return new AuthResponse(token, user.getId(), user.getEmail(), profile.getDisplayName());
    }

    public AuthResponse signin(SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        Profile profile = profileRepository.findById(user.getId())
                .orElse(new Profile()); // Should theoretically exist

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        return new AuthResponse(token, user.getId(), user.getEmail(), profile.getDisplayName());
    }
}
