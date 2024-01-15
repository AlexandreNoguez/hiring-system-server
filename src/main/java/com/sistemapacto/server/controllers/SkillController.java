package com.sistemapacto.server.controllers;

import com.sistemapacto.server.dto.skill.SkillCreateDTO;
import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/skill")
public class SkillController {
    private final SkillService skillService;

    @PostMapping("/register")
    public ResponseEntity<SkillDTO> createNewSkill(@Valid @RequestBody SkillCreateDTO skillCreateDTO)
            throws BusinessException, IOException {
        return new ResponseEntity<>(skillService.createNewSkill(skillCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable Long skillId,
            @RequestBody SkillCreateDTO updatedSkillDTO) throws BusinessException, IOException {
        SkillDTO updatedSkill = skillService.updateSkill(skillId, updatedSkillDTO);
        return new ResponseEntity<>(updatedSkill, HttpStatus.OK);

    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) throws BusinessException {
        skillService.deleteSkill(skillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
