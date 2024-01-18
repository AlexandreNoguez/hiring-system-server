package com.sistemapacto.server.controllers;

import com.sistemapacto.server.controllers.doc.SkillDoc;
import com.sistemapacto.server.dto.skill.SkillCreateDTO;
import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.services.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/skill")
public class SkillController implements SkillDoc {
    private final SkillService skillService;

    @PostMapping("/register")
    public ResponseEntity<SkillDTO> createNewSkill(@Valid @RequestBody SkillCreateDTO skillCreateDTO) throws BusinessException, IOException {
        log.info("Iniciando cadastro de habilidade - createNewSkill() . . .");
        return new ResponseEntity<>(skillService.createNewSkill(skillCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        log.info("Iniciando busca de todas habilidades - getAllSkills() . . .");
        List<SkillDTO> skills = skillService.getAllSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable Long skillId,
            @RequestBody SkillCreateDTO updatedSkillDTO) throws BusinessException, IOException {
        log.info("Iniciando atualização de habilidade - updateSkill() . . .");
        SkillDTO updatedSkill = skillService.updateSkill(skillId, updatedSkillDTO);
        return new ResponseEntity<>(updatedSkill, HttpStatus.OK);

    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long skillId) throws BusinessException {
        log.info("Iniciando remoação de habilidade - deleteSkill() . . .");
        skillService.deleteSkill(skillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
