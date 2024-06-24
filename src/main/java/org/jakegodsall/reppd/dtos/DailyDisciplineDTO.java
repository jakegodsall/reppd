package org.jakegodsall.reppd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DailyDisciplineDTO extends BaseDto {

    private String title;
    private String description;
    private Status status;
    private Long minimumValue;

}
