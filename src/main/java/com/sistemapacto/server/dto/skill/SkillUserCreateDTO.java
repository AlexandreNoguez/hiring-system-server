package com.sistemapacto.server.dto.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillUserCreateDTO {
    @Schema(description = "Id da habilidade")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private Long skillId;
}
