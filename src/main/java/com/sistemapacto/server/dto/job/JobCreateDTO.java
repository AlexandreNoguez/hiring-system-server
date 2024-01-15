package com.sistemapacto.server.dto.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobCreateDTO {

    @Schema(description = "Título da vaga a cadastrar", example = "Desenvolvedor Backend")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String title;

    @Schema(description = "Login do usuário", example = "Alexandre.Noguez")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String description;

    @Schema(description = "Login do usuário", example = "Alexandre.Noguez")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String requirements;

    @Schema(description = "Login do usuário", example = "Alexandre.Noguez")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String status;

}
