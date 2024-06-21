package org.jakegodsall.reppd.entities.dailydiscipline;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@DiscriminatorValue("TIME")
public class TimeDailyDiscipline extends DailyDiscipline {
}
