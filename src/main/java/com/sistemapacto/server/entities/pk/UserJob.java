package com.sistemapacto.server.entities.pk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemapacto.server.entities.JobEntity;
import com.sistemapacto.server.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Entity(name = "user_job")
public class UserJob {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_job_id", updatable = false, nullable = false)
    private UUID userJobId;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "certification")
    private String certifications;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobEntity jobEntity;

}
