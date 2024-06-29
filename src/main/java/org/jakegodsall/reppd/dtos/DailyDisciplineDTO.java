package org.jakegodsall.reppd.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class DailyDisciplineDTO extends BaseDto {

    private String title;
    private String description;
    private Status status;
    private Long minimumValue;
    private CompetencyDTO competency;
}
