package com.sistemapacto.server.controllers.doc;

import com.sistemapacto.server.dto.user.UserCreateDTO;
import com.sistemapacto.server.dto.user.UserDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;

public interface UserDoc {

    @Operation(summary = "Cadastro de usuário no sistema", description = "Realiza o cadastro de usuário no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserCreateDTO userCreateDTO) throws BusinessException, IOException;
}
