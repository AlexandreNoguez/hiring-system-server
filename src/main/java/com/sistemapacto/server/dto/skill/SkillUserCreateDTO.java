package com.sistemapacto.server.dto.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillUserCreateDTO {
    @Schema(description = "Id da habilidade")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private Long skillId;

    @Schema(description = "Experiência do usuário com a habilidade.")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String experience;

    @Schema(description = "Último contato com a habilidade.")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private LocalDateTime lastUsed;
}
