package org.jakegodsall.reppd.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.DailyDiscipline;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DailyLogDTO extends BaseDTO {
    @NotNull
    private LocalDateTime date;

    private DailyDiscipline dailyDiscipline;

    @NotNull
    private Long value;
}
