package com.sistemapacto.server.dto.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillCreateDTO {
//    private Long skillId;

    @Schema(description = "Id da habilidade")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String title;

//    @Schema(description = "Login do usuário", example = "Alexandre.Noguez")
//    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
//    private Set<SkillUser> skillUsers;
}
