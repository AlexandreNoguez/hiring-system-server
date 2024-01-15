package com.sistemapacto.server.entities;

import com.sistemapacto.server.entities.pk.SkillUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "skills")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", updatable = false, nullable = false)
    private Long skillId;

    @Column(name = "skill_title")
    private String title;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "skillEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SkillUser> skillUsers;

}
