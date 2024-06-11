package org.jakegodsall.reppd.entities;

import org.jakegodsall.reppd.entities.enums.DisciplineType;
import org.jakegodsall.reppd.entities.enums.Frequency;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class DailyDiscipline extends BaseEntity {
    private String title;
    private String description;

    private DisciplineType disciplineType;
    private Frequency frequency;

    private Integer minTarget;
}
