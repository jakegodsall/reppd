package org.jakegodsall.reppd.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class LogDetails {
    private Long numericValue;
    private Long timeInMinutesValue;
    private Boolean booleanValue;
}
