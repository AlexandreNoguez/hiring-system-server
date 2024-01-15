package com.sistemapacto.server.controllers;

import com.sistemapacto.server.dto.job.JobCreateDTO;
import com.sistemapacto.server.dto.job.JobDTO;
import com.sistemapacto.server.dto.jobuser.JobUserCreateDTO;
import com.sistemapacto.server.dto.jobuser.JobUserDTO;
import com.sistemapacto.server.dto.pagination.PaginationDTO;
import com.sistemapacto.server.entities.pk.UserJob;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;
    @PostMapping("/register")
    public ResponseEntity<JobDTO> createNewJob(@Valid @RequestBody JobCreateDTO jobCreateDTO)
            throws BusinessException, IOException {
        return new ResponseEntity<>(jobService.createNewJob(jobCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAllByParams")
    public ResponseEntity<PaginationDTO<JobDTO>> getAllJobs(
            @RequestParam(required = false) Integer jobId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String requirements,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) throws BusinessException {
        return new ResponseEntity<>(jobService.getAllJobs(jobId, title, description, requirements, status, page, size), HttpStatus.OK);

    }

    @GetMapping("/getById/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long jobId) {
            Optional<JobDTO> job = jobService.getJobById(jobId);
            return job.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PatchMapping("/application/{jobId}")
    public ResponseEntity<JobUserDTO> userApplication(
            @PathVariable Long jobId
//            @RequestBody JobUserCreateDTO jobUserCreateDTO
    ) throws BusinessException {
        JobUserDTO application = jobService.candidateApplication(jobId);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }
//
//    @PutMapping("/{skillId}")
//    public ResponseEntity<SkillDTO> updateSkill(
//            @PathVariable Long skillId,
//            @RequestBody SkillCreateDTO updatedSkillDTO) throws BusinessException, IOException {
//        SkillDTO updatedSkill = skillService.updateSkill(skillId, updatedSkillDTO);
//        return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("/{skillId}")
//    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) throws BusinessException {
//        skillService.deleteSkill(skillId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
}
