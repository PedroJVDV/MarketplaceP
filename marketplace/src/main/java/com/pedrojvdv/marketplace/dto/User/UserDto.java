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
public class UserDto {

    public String name;
    public String email;
    public String usernameLogin;
    public String password;
    public Integer age;
    private UserRole userRole;
}
