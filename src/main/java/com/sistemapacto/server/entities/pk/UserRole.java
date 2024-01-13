package com.sistemapacto.server.entities.pk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemapacto.server.entities.RoleEntity;
import com.sistemapacto.server.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Entity(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_role_id", updatable = false, nullable = false)
    private UUID userRoleId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
}
