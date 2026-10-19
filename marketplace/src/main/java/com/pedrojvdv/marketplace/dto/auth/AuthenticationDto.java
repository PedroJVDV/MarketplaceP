package com.pedrojvdv.marketplace.dto.auth;


import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    @Email
    private String email;
    private String usernameLogin;
    private String password;
}
