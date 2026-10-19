package com.pedrojvdv.marketplace.dto.auth;

import com.pedrojvdv.marketplace.enums.User.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@NotBlank
public class RegisterResponseDto {

    String username;
    @Email
    String email;
    String usernameLogin;
}
