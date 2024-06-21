package org.jakegodsall.reppd.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.dailydiscipline.DailyDiscipline;
import org.jakegodsall.reppd.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@Entity
public class Competency extends BaseEntity {

    @NotBlank
    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String title;

    @Size(max = 1000)
    @Column(length = 1000)
    private String description;

    @NotNull
    private Status status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "competency")
    private Set<DailyDiscipline> dailyDisciplines;
}
