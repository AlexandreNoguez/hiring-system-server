package com.sistemapacto.server.services;

import com.sistemapacto.server.entities.RoleEntity;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity buscarPorIdCargo(Long roleId) throws BusinessException {
        return roleRepository.findById(roleId)
                .orElseThrow(()-> new BusinessException("Cargo não encontrado"));
    }

    public RoleEntity findByRoleName (String roleName) throws BusinessException {
        if (roleName.isEmpty()) {
            throw new BusinessException("Nome do cargo não pode estar vazio");
        }

        return roleRepository.findAllByRoleNameContainingIgnoreCase(roleName)
                .orElseThrow(() -> new BusinessException("Cargo não encontrado"));
    }
}
