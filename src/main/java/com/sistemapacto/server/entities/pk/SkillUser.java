package com.sistemapacto.server.entities.pk;

import com.sistemapacto.server.entities.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemapacto.server.entities.SkillEntity;
import org.hibernate.annotations.GenericGenerator;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "skill_user")
public class SkillUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "skill_user_id", updatable = false, nullable = false)
    private UUID skillUserId;

//    @Column(name = "user_id")
//    private UUID userId;

    @Column(name = "experience_level")
    private String experience;

    @Column(name = "last_used_date")
    private LocalDate lastUsed;

    @Column(name = "certification")
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private SkillEntity skillEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
