package com.sistemapacto.server.entities;

import com.sistemapacto.server.entities.pk.UserJob;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "job_id", updatable = false, nullable = false)
    private UUID jobId;

    @Column(name = "job_title")
    private String title;

    @Column(name = "job_description")
    private String description;

    @Column(name = "job_requirements")
    private String requirements;

    @Column(name = "job_status")
    private String status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "jobEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserJob> userJobs;



}
