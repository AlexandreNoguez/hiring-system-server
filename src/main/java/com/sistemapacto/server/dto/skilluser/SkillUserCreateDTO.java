package com.sistemapacto.server.dto.skilluser;

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
    @Schema(description = "Email do usuário", example = "alexandrenoguez@email.com")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private String experience;

    @Schema(description = "Email do usuário", example = "alexandrenoguez@email.com")
    @NotBlank(message = "Campo obrigatório, verifique e tente novamente.")
    private LocalDateTime lastUsed;

//    private SkillEntity skillEntity;
//    private UserEntity userEntity;

}
