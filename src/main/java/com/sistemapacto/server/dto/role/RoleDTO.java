package com.sistemapacto.server.dto.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleDTO {
    @NotNull
    private String roleName;
}
