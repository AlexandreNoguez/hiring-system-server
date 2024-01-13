package com.sistemapacto.server.entities;

import com.sistemapacto.server.entities.pk.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "roles")
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "role_id", updatable = false, nullable = false)
    private UUID roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY)
    private Set<UserRole> userRole;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
