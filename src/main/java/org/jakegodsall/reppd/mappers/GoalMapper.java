package org.jakegodsall.reppd.mappers;

import org.jakegodsall.reppd.dtos.GoalDto;
import org.jakegodsall.reppd.entities.Goal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    public GoalDto goalToGoalDto(Goal goal);
    public Goal goalDtoToGoal(GoalDto goalDto);
}
