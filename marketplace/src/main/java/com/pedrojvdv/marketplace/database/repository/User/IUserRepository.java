package com.pedrojvdv.marketplace.database.repository.User;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByUsernameLogin(String login);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByRole(UserRole userRole);

    List<UserEntity> findByName(String username);

}
