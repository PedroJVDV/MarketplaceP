package com.pedrojvdv.marketplace.dto.User;

import com.pedrojvdv.marketplace.enums.User.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    // ONLY FOR INPUT RESPONSES
    @NotBlank
    @NotNull
    private String usernameLogin;
    @NotBlank
    @NotNull
    private String email;
    private String username;
    @NotBlank
    @NotNull
    private UserRole userRole;
    @NotBlank
    @NotNull
    private String password;
}
