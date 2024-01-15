package com.sistemapacto.server.services;

import com.sistemapacto.server.dto.skill.SkillCreateDTO;
import com.sistemapacto.server.dto.skill.SkillDTO;
import com.sistemapacto.server.entities.SkillEntity;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;
    private ZoneId ZONE_BRAZIL = ZoneId.of("America/Sao_Paulo");
    public SkillDTO createNewSkill(SkillCreateDTO skillCreateDTO) throws BusinessException, IOException{
        Optional<SkillEntity> skill = skillRepository.findByTitleIgnoreCase(skillCreateDTO.getTitle());

        if(skill.isPresent()){
            throw new BusinessException("Habilidade já cadastrada");
        }

        SkillEntity skillEntity = skillConverterEntity(skillCreateDTO);

        skillEntity.setCreatedAt(ZonedDateTime.now(ZONE_BRAZIL).toLocalDateTime());

        skillRepository.save(skillEntity);
        return skillConvertDTO(skillEntity);
    }

    public List<SkillDTO> getAllSkills() {
        List<SkillEntity> skills = skillRepository.findAll();
        return skills.stream()
                .map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
    }

    public SkillDTO updateSkill(Long skillId, SkillCreateDTO updatedSkillDTO) throws BusinessException, IOException {
        SkillEntity existingSkill = skillRepository.findById(skillId)
                .orElseThrow(() -> new BusinessException("Habilidade não encontrada"));

        existingSkill.setTitle(updatedSkillDTO.getTitle());

        SkillEntity updatedSkill = skillRepository.save(existingSkill);
        return skillConvertDTO(updatedSkill);
    }

    public void deleteSkill(Long skillId) throws BusinessException {
        Optional<SkillEntity> skillOptional = skillRepository.findById(skillId);

        if (skillOptional.isPresent()) {
            skillRepository.deleteById(skillId);
        } else {
            throw new BusinessException("Habilidade não encontrada para exclusão");
        }
    }

    private SkillEntity skillConverterEntity(SkillCreateDTO skillCreateDTO) throws IOException {
        return modelMapper.map(skillCreateDTO,SkillEntity.class);
    }

    private SkillDTO skillConvertDTO(SkillEntity skillEntity) {
        return new SkillDTO(skillEntity.getSkillId(), skillEntity.getTitle());
    }

}
