package org.jakegodsall.reppd.dtos;

import jakarta.validation.constraints.NotNull;
import org.jakegodsall.reppd.entities.DailyDiscipline;

import java.time.LocalDateTime;

public class DailyLogDTO extends BaseDTO {
    @NotNull
    private LocalDateTime date;

    private DailyDiscipline dailyDiscipline;

    @NotNull
    private Long value;
}
