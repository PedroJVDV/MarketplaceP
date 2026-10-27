package com.pedrojvdv.marketplace.dto.User;

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
public class UserResponseDto {

    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String username;

    private Integer age;
    @NotBlank
    @NotNull
    private String password;
}
