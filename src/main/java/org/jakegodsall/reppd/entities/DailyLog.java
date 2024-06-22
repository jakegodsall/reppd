package org.jakegodsall.reppd.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
public class DailyLog extends BaseEntity {

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "daily_discipline_id")
    private DailyDiscipline dailyDiscipline;

    private Long value;
}
