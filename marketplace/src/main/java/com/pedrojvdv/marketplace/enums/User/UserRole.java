package com.pedrojvdv.marketplace.enums.User;

public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER"),
    SELLER("SELLER");

    private final String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole() {
        return role;
    }

}
