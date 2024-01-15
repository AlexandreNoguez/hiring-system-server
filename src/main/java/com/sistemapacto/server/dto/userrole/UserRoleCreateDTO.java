package com.sistemapacto.server.dto.userrole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleCreateDTO {
    @Schema(description = "Tipo de cargo para permissão do usuário", example = "ROLE_ADMIN")
    private String roleName;
}
