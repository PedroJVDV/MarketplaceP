package com.pedrojvdv.marketplace.dto.auth;

import com.pedrojvdv.marketplace.enums.User.UserRole;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    String username;
    String email;
    String usernameLogin;
    String password;
    UserRole role;

}
