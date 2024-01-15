package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserLogin(String userLogin);

    Optional<UserEntity> findByUserEmail(String userEmail);
}
