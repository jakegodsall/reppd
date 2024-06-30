package org.jakegodsall.reppd.mappers;

import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.entities.Competency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetencyMapper {
    public CompetencyDTO competencyToCompetencyDTO(Competency competency);
    public Competency competencyDTOtoCompetency(CompetencyDTO competencyDTO);
}
