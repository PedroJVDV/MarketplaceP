package com.pedrojvdv.marketplace.dto.auth;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String email;
    private String usernameLogin;
    private String password;
}
