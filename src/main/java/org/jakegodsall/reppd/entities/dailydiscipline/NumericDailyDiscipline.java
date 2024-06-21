package org.jakegodsall.reppd.entities.dailydiscipline;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NUMERIC")
public class NumericDailyDiscipline extends DailyDiscipline {

}
