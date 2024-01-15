package com.sistemapacto.server.dto.jobuser;

import com.sistemapacto.server.dto.job.JobDTO;
import com.sistemapacto.server.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobUserDTO {
    private Long userJobId;
    private String applicationStatus;
    private LocalDateTime applicationDate;
    private UserDTO userDTO;
    private JobDTO jobDTO;
}
