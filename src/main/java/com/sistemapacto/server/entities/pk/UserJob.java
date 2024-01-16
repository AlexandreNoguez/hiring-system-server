package com.sistemapacto.server.entities.pk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemapacto.server.entities.JobEntity;
import com.sistemapacto.server.entities.UserEntity;
import com.sistemapacto.server.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Entity(name = "user_job")
public class UserJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_job_id", updatable = false, nullable = false)
    private Long userJobId;

    @Column(name = "application_status")
    private ApplicationStatus applicationStatus;

    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobEntity jobEntity;

}
