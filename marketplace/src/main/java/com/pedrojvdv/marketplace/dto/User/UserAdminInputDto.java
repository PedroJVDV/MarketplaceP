package com.pedrojvdv.marketplace.dto.User;

import com.pedrojvdv.marketplace.enums.User.UserRole;
import jakarta.validation.constraints.Email;
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
public class UserAdminInputDto {

    @NotNull
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String usernameLogin;
    private Integer age;
    @NotNull
    @NotBlank
    private UserRole userRole;
}
