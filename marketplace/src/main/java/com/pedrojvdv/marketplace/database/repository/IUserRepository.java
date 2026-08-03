package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.UserEntity;
import com.pedrojvdv.marketplace.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByRole(Role role);
    List<UserEntity> findByName(String username);

}
