package com.hanarae.availo.controller;

import com.hanarae.availo.domain.User;
import com.hanarae.availo.dto.UserRequestDto;
import com.hanarae.availo.dto.UserResponseDto;
import com.hanarae.availo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "회원가입", description = "email, password, nickname을 입력받아 회원가입 처리")
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            return ResponseEntity.badRequest().build(); // 이메일 중복
        }

        User newUser = User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .nickname(dto.nickname())
                .role("ROLE+USER")
                .build();

        User saved = userRepository.save(newUser);
        return ResponseEntity.ok(new UserResponseDto(saved.getId(), saved.getEmail(), saved.getNickname()));
    }
}
