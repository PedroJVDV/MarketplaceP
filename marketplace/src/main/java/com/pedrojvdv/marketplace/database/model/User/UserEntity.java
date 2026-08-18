package com.pedrojvdv.marketplace.database.model.User;


import com.pedrojvdv.marketplace.database.model.Order.OrderEntity;
import com.pedrojvdv.marketplace.database.model.Product.ProductEntity;
import com.pedrojvdv.marketplace.database.model.Sale.SaleEntity;
import com.pedrojvdv.marketplace.database.model.Wish.WishListEntity;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email(message = "Email inválido")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true)
    private String usernameLogin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

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
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return (List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public UserEntity(String name, String email, String userNameLogin, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.usernameLogin = userNameLogin;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
