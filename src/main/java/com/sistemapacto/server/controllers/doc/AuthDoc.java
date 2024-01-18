package com.sistemapacto.server.controllers.doc;

import com.sistemapacto.server.dto.login.LoginDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthDoc {

    @Operation(summary = "Autenticação do usuário no sistema", description = "Realiza a autenticação do usuário no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @PostMapping
    public String authenticate(@RequestBody @Valid LoginDTO loginDTO) throws BusinessException;
}
