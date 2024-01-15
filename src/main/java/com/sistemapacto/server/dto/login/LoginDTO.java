package com.sistemapacto.server.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    @NotNull
    @Schema(example = "alexandre.noguez")
    private String userLogin;
    @NotNull
    @Schema(example = "123aCB!!!")
    private String userPassword;
}
