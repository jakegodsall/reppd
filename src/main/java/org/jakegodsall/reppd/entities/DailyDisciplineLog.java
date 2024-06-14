package org.jakegodsall.reppd.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@Entity
public class DailyDisciplineLog extends BaseEntity {

    private LocalDateTime date;
    private Long value;

}
