package com.pedrojvdv.marketplace.database.repository;

import com.pedrojvdv.marketplace.database.model.UserEntity;
import com.pedrojvdv.marketplace.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByName(String username);
    List<UserEntity> findByRole(Role role);

}
