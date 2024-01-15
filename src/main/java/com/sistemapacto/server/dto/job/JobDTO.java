package com.sistemapacto.server.dto.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobDTO {
    private Long jobId;
    private String title;
    private String description;
    private String requirements;
    private String status;

}
