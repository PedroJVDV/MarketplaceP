package com.pedrojvdv.marketplace.database.model.User;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedrojvdv.marketplace.database.model.Order.OrderEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.Sale.SaleEntity;
import com.pedrojvdv.marketplace.database.model.Wish.WishListEntity;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @NotEmpty
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email(message = "Email inválido")
    @Column(unique = true, nullable = false)
    @NotNull
    @NotEmpty
    private String email;

    @Column(nullable = false, length = 255)
    @NotNull
    @NotEmpty
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String usernameLogin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private UserRole role = UserRole.USER;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<OrderEntity> orders = new HashSet<>();

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<SaleEntity> sale = new HashSet<>();

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<WishListEntity> wishList = new HashSet<>();

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<ProductEntity> product = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    public UserEntity(String name, String email, String userNameLogin, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.usernameLogin = userNameLogin;
        this.password = password;
        this.role = role;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return usernameLogin;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
