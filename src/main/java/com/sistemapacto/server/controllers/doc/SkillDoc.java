package com.sistemapacto.server.controllers.doc;

import com.sistemapacto.server.dto.skill.SkillCreateDTO;
import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public interface SkillDoc {

    @Operation(summary = "Cadastro de habilidades no sistema", description = "Realiza o cadastro de habilidade no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<SkillDTO> createNewSkill(@Valid @RequestBody SkillCreateDTO skillCreateDTO)
            throws BusinessException, IOException;

    @Operation(summary = "Busca todas habilidades no sistema", description = "Realiza a busca de todas as habilidades do sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills();

    @Operation(summary = "Busca habilidade por id no sistema", description = "Realiza a busca de habilidade por id no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable Long skillId,
            @RequestBody SkillCreateDTO updatedSkillDTO) throws BusinessException, IOException;

    @Operation(summary = "Remove habilidade no sistema", description = "Realiza a remoção de habilidade no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Remoção realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) throws BusinessException;
}
