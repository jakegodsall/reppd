package org.jakegodsall.reppd.mappers;

import org.jakegodsall.reppd.dtos.DailyLogDTO;
import org.jakegodsall.reppd.entities.DailyLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyLogMapper {
    DailyLogDTO dailyLogToDailyLogDTO(DailyLog dailyLog);
    DailyLog dailyLogDTOToDailyLog(DailyLogDTO dailyLogDTO);
}
