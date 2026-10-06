package com.pedrojvdv.marketplace.enums.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER"),
    SELLER("SELLER");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

}
