package org.jakegodsall.reppd.mappers;

import org.jakegodsall.reppd.dtos.CompetencyDTO;
import org.jakegodsall.reppd.entities.Competency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetencyMapper {
    public CompetencyDTO goalToGoalDto(Competency goal);
    public Competency goalDtoToGoal(CompetencyDTO competencyDTO);
}
