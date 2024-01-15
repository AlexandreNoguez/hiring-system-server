package com.sistemapacto.server.repositories;

import com.sistemapacto.server.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findAllByRoleNameContainingIgnoreCase(String roleName);
}
