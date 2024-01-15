package com.sistemapacto.server.entities.pk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemapacto.server.entities.SkillEntity;
import com.sistemapacto.server.entities.UserEntity;
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
@Entity(name = "skill_user")
public class SkillUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_user_id", updatable = false, nullable = false)
    private Long skillUserId;

//    @Column(name = "user_id")
//    private Long userId;

    @Column(name = "experience_level")
    private String experience;

    @Column(name = "last_used_date")
    private LocalDateTime lastUsed;

    @Column(name = "certification")
    private String description;

//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private SkillEntity skillEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
