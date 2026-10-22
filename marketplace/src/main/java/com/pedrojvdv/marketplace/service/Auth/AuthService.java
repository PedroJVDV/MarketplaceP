package com.pedrojvdv.marketplace.service.Auth;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.database.repository.User.IUserRepository;
import com.pedrojvdv.marketplace.dto.auth.AuthenticationDto;
import com.pedrojvdv.marketplace.dto.auth.LoginResponseDto;
import com.pedrojvdv.marketplace.dto.auth.RegisterRequestDto;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import com.pedrojvdv.marketplace.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;

    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> loginAuth(AuthenticationDto dataDto) {

        var credentials = new UsernamePasswordAuthenticationToken(dataDto.getUsernameLogin(), dataDto.getPassword());
        var auth = this.authenticationManager.authenticate(credentials);

        var token = tokenService.generateToken((UserEntity)
                Objects.requireNonNull(auth.getPrincipal()));

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PreAuthorize("permitAll()")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Void> registerAuth(RegisterRequestDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent() || userRepository.findByUsernameLogin(registerDto.getUsernameLogin()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());

        UserEntity userEntity = new UserEntity(
                registerDto.getUsername(),
                registerDto.getEmail(),
                registerDto.getUsernameLogin(),
                encodedPassword,
                UserRole.USER
        );

        this.userRepository.save(userEntity);
        return ResponseEntity.ok().build();
    }
}
