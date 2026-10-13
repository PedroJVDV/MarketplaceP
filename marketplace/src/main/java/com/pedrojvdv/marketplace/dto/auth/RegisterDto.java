package com.pedrojvdv.marketplace.dto.auth;

import com.pedrojvdv.marketplace.enums.User.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@NotBlank
public class RegisterDto {

    String username;
    @Email
    String email;
    String usernameLogin;
    String password;
}
