package com.sistemapacto.server.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @Schema(description = "Login do usuário", example = "Alexandre.Noguez")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userLogin;

    @Schema(description = "Senha do usuário", example = "12365185436")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userPassword;

    @Schema(description = "Email do usuário", example = "alexandrenoguez@email.com")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userEmail;

    @Schema(description = "Cargo do usuário", example = "Recrutador ou Candidato")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String userRole;


}
