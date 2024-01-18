package com.sistemapacto.server.controllers.doc;

import com.sistemapacto.server.dto.job.JobCreateDTO;
import com.sistemapacto.server.dto.job.JobDTO;
import com.sistemapacto.server.dto.jobuser.JobUserDTO;
import com.sistemapacto.server.dto.pagination.PaginationDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface JobDoc {

    @Operation(summary = "Cadastro de vaga no sistema", description = "Realiza o cadastro de vaga no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<JobDTO> createNewJob(@Valid @RequestBody JobCreateDTO jobCreateDTO)
            throws BusinessException;

    @Operation(summary = "Busca de vaga no sistema", description = "Realiza a busca de todas as vagas no sistema se enviada sem parâmetro ou busca todas as vagas encontradas de acordo com o parâmetro")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @GetMapping("/getAllByParams")
    public ResponseEntity<PaginationDTO<JobDTO>> getAllJobs(
            @RequestParam(required = false) Long jobId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String requirements,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) throws BusinessException;

    @Operation(summary = "Busca vaga por id no sistema", description = "Realiza a busca por id da vaga no sistema")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @GetMapping("/getById/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long jobId);

    @Operation(summary = "Aplica o usuário na vaga", description = "Realiza a aplicação do candidato na vaga")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Aplicação realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção durante a execução deste recurso")
            }
    )
    @PatchMapping("/application/{jobId}")
    public ResponseEntity<JobUserDTO> userApplication(@PathVariable Long jobId) throws BusinessException;
}
