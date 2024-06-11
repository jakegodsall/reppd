package org.jakegodsall.reppd.entities;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Metric extends BaseEntity {
    private String name;
    private String unit;
}
