package org.jakegodsall.reppd.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LogDetails {
    private Long numericValue;
    private Long timeInMinutesValue;
    private Boolean booleanValue;
}
