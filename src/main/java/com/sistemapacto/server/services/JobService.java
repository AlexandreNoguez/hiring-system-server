package com.sistemapacto.server.services;

import com.sistemapacto.server.dto.job.JobCreateDTO;
import com.sistemapacto.server.dto.job.JobDTO;
import com.sistemapacto.server.dto.jobuser.JobUserDTO;
import com.sistemapacto.server.dto.pagination.PaginationDTO;
import com.sistemapacto.server.dto.user.UserDTO;
import com.sistemapacto.server.entities.JobEntity;
import com.sistemapacto.server.entities.UserEntity;
import com.sistemapacto.server.entities.pk.UserJob;
import com.sistemapacto.server.enums.ApplicationStatus;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final UserService userService;
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;

    private final ZoneId ZONE_BRAZIL = ZoneId.of("America/Sao_Paulo");

    public JobDTO createNewJob(JobCreateDTO jobCreateDTO) throws BusinessException {
        JobEntity jobEntity = jobConverterEntity(jobCreateDTO);

        UserEntity recuiter = userService.getUserById(userService.getLoggedUser().getUserId());

        jobEntity.setUserRecruiterEmail(recuiter.getUserEmail());

        jobEntity.setCreatedAt(ZonedDateTime.now(ZONE_BRAZIL).toLocalDateTime());

        jobRepository.save(jobEntity);
        return jobSkillConvertDTO(jobEntity);
    }

    public PaginationDTO<JobDTO> getAllJobs(
            Long jobId,
            String title,
            String description,
            String requirements,
            String status,
            Integer page,
            Integer size) throws BusinessException {
        if (page < 0 || size < 0) {
            throw new BusinessException("Tamanho da página ou de elementos não podem ser menor que 0.");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<JobEntity> jobEntities = jobRepository.getAllJobs(jobId, title, description, requirements, status, pageRequest);

        List<JobDTO> jobsDTO = jobEntities.getContent()
                .stream()
                .map(this::jobConvertDTO)
                .collect(Collectors.toList());

        if (jobsDTO.isEmpty()) {
            throw new BusinessException("Dados não encontrados.");
        }

        return new PaginationDTO<>(jobEntities.getTotalElements(),
                jobEntities.getTotalPages(),
                page,
                size,
                jobsDTO);
    }

    public Optional<JobDTO> getJobById(Long jobId) {
        Optional<JobEntity> jobEntity = jobRepository.findById(jobId);
        return jobEntity.map(this::jobConvertDTO);
    }

    public JobUserDTO candidateApplication(Long jobId) throws BusinessException {


        UserEntity userEntity = userService.getUserById(userService.getLoggedUser().getUserId());

        if(jobId == null){
            throw new BusinessException("Id da vaga inválido");
        }


        JobEntity jobEntity = findJobById(jobId);
        if (jobEntity.getUserJobs().stream().anyMatch(userJob ->
                userJob.getUserEntity().equals(userEntity) && userJob.getJobEntity().equals(jobEntity))) {
            throw new BusinessException("Usuário já está cadastrado para esta vaga");
        }

        UserJob userJob = new UserJob();
        userJob.setUserEntity(userEntity);
        userJob.setJobEntity(jobEntity);
        userJob.setApplicationStatus(ApplicationStatus.SENT);
        Set<UserJob> setUserJob = new HashSet<>();
        setUserJob.add(userJob);

        jobEntity.setUserJobs(setUserJob);

        userJob.setApplicationDate(LocalDateTime.now());

        UserDTO recruiter = userService.findByUserEmail(jobEntity.getUserRecruiterEmail());

        notificationService.sendApplicationNotification(modelMapper.map(userEntity, UserDTO.class), recruiter, modelMapper.map(jobEntity, JobDTO.class));
        jobRepository.save(jobEntity);

        return modelMapper.map(userJob, JobUserDTO.class);
    }

    private JobDTO jobConvertDTO(JobEntity jobEntity) {
        return modelMapper.map(jobEntity, JobDTO.class);
    }

    private JobEntity jobConverterEntity(JobCreateDTO jobCreateDTO) {
        return modelMapper.map(jobCreateDTO, JobEntity.class);
    }

    private JobDTO jobSkillConvertDTO(JobEntity jobEntity) {

        return new JobDTO(jobEntity.getJobId(),
                jobEntity.getTitle(),
                jobEntity.getDescription(),
                jobEntity.getRequirements(),
                jobEntity.getStatus()
                );
    }

    public JobEntity findJobById(Long jobId) throws BusinessException{
        return jobRepository.findById(jobId).orElseThrow(()-> new BusinessException("Vaga não encontrada") );
    }
}
