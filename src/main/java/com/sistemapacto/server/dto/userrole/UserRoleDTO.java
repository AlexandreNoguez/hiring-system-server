package com.sistemapacto.server.dto.userrole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleDTO {
    private Long roleId;
    private String roleName;
}
