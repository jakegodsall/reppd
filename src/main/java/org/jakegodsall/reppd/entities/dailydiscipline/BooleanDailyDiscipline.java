package org.jakegodsall.reppd.entities.dailydiscipline;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOLEAN")
public class BooleanDailyDiscipline extends DailyDiscipline {
}
