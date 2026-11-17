package com.pedrojvdv.marketplace.dto.auth;

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
public class RegisterRequestDto {

    private String username;
    @Email
    private String email;
    private String usernameLogin;
    private String password;
    private Integer age;

    // DEFAULT ROLE == USER!
}
