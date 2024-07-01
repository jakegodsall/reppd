package org.jakegodsall.reppd.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String description;

    @NotNull
    private Status status;

    private Long minimumValue;

    private CompetencyDTO competency;
}
