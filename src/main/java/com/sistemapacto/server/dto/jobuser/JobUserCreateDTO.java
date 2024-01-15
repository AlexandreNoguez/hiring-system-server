package com.sistemapacto.server.dto.jobuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobUserCreateDTO {
    @NotBlank
    private Long jobId;

}
