package com.sistemapacto.server.dto.user;

import com.sistemapacto.server.dto.skill.SkillUserCreateDTO;
import com.sistemapacto.server.dto.userrole.UserRoleCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {
    @Schema(description = "Login do usuário", example = "alexandre.noguez")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userLogin;

    @Schema(description = "Nome do usuário", example = "Alexandre Noguez")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userName;

    @Schema(description = "Senha do usuário", example = "123aCB!!!")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userPassword;

    @Schema(description = "Senha do usuário repetida", example = "123aCB!!!")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String matchUserPassword;

    @Schema(description = "Email do usuário", example = "alexandrenoguez@email.com")
    @NotBlank(message = "Deve ser um formato de e-mail válido.")
    //    @Email manterei comentado para fins de teste com qualquer email
    private String userEmail;

    @Schema(description = "Cargo do usuário")
    private Set<UserRoleCreateDTO> roles;

    @Schema(description = "Habilidades do usuário")
    private Set<SkillUserCreateDTO> skills;
}
