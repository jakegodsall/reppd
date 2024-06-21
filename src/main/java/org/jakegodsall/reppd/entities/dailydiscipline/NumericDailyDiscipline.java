package org.jakegodsall.reppd.entities.dailydiscipline;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@DiscriminatorValue("NUMERIC")
public class NumericDailyDiscipline extends DailyDiscipline {

}
