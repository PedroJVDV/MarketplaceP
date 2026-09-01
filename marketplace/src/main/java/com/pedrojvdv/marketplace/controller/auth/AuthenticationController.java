package com.pedrojvdv.marketplace.controller.auth;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.User.UserDto;
import com.pedrojvdv.marketplace.dto.auth.AuthenticationDto;
import com.pedrojvdv.marketplace.dto.auth.LoginResponseDto;
import com.pedrojvdv.marketplace.dto.auth.RegisterDto;
import com.pedrojvdv.marketplace.service.token.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dataDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dataDto.getUsernameLogin(), dataDto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent() || userRepository.findByUsernameLogin(registerDto.getUsernameLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String password = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        UserEntity userEntity = new UserEntity(registerDto.getUsername(), registerDto.getEmail(),registerDto.getUsernameLogin(), password, registerDto.getRole());

        this.userRepository.save(userEntity);

        return ResponseEntity.ok().build();
    }
}
