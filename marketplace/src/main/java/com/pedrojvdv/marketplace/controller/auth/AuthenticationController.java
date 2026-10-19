package com.pedrojvdv.marketplace.controller.auth;

import com.pedrojvdv.marketplace.dto.auth.AuthenticationDto;
import com.pedrojvdv.marketplace.dto.auth.RegisterRequestDto;

import com.pedrojvdv.marketplace.service.Auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto dataDto) {
        return authService.loginAuth(dataDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto registerDto) {
        return authService.registerAuth(registerDto);
    }
}
