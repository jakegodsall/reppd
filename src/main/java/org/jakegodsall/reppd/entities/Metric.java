package org.jakegodsall.reppd.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@Entity
public class Metric extends BaseEntity {
    private String name;
    private String unit;
}
