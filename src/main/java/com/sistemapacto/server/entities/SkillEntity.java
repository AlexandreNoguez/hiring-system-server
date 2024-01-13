package com.sistemapacto.server.entities;

import com.sistemapacto.server.entities.pk.SkillUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import java.util.Set;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "skills")
public class SkillEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "skill_id", updatable = false, nullable = false)
    private UUID skillId;

    @Column(name = "skill_title")
    private String title;

    @Column(name = "skill_description")
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "skillEntity", fetch = FetchType.LAZY)
    private Set<SkillUser> skillUsers;

}
