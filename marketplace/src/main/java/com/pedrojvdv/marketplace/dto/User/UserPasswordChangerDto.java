package com.pedrojvdv.marketplace.dto.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordChangerDto {
    @NotNull
    private String password;
    @NotNull
    private String newPassword;
    @NotNull
    private String confirmNewPassword;
}
