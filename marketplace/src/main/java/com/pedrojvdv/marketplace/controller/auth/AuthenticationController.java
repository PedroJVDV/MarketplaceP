package com.pedrojvdv.marketplace.controller.auth;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.auth.AuthenticationDto;
import com.pedrojvdv.marketplace.dto.auth.RegisterDto;
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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/v1/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dataDto) {
        var userEmailPassword = new UsernamePasswordAuthenticationToken(dataDto.getEmail(), dataDto.getPassword());
        var auth = this.authenticationManager.authenticate(userEmailPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/register")
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
