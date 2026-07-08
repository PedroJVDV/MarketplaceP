package com.pedrojvdv.marketplace.dto;

import com.pedrojvdv.marketplace.enums.Role;
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
    public String password;
    public Integer age;
    private Role role;
}
