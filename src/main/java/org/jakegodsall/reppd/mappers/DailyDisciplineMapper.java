package org.jakegodsall.reppd.mappers;

import org.jakegodsall.reppd.dtos.DailyDisciplineDTO;
import org.jakegodsall.reppd.entities.DailyDiscipline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyDisciplineMapper {
    public DailyDisciplineDTO dailyDisciplineToDailyDisciplineDTO(DailyDiscipline dailyDiscipline);
    public DailyDiscipline dailyDisciplineDTOToDailyDiscipline(DailyDisciplineDTO dailyDisciplineDTO);
}
