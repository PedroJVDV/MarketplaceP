package com.pedrojvdv.marketplace.dto.User;

import com.pedrojvdv.marketplace.enums.User.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String usernameLogin;
    private Integer age;
    private UserRole userRole;
}
