package com.pedrojvdv.marketplace.database.repository.User;

import com.pedrojvdv.marketplace.database.model.User.UserEntity;
import com.pedrojvdv.marketplace.enums.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {


    @Query("SELECT u FROM UserEntity u WHERE u.usernameLogin = ?1")
    UserDetails findByUsernameLogin(String login);

    Optional<UserEntity> findUserEntityByUsernameLogin(String login);

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.role = ?1")
    List<UserEntity> findByRole(UserRole userRole);


}
