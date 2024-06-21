package org.jakegodsall.reppd.entities.dailydiscipline;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TIME")
public class TimeDailyDiscipline extends DailyDiscipline {
}
