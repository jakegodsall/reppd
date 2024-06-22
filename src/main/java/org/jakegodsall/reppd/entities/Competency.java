package org.jakegodsall.reppd.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

    @Builder.Default
    @OneToMany(mappedBy = "competency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DailyDiscipline> dailyDisciplines = new HashSet<>();

    public void addDailyDiscipline(DailyDiscipline dailyDiscipline) {
        this.dailyDisciplines.add(dailyDiscipline);
        dailyDiscipline.setCompetency(this);
    }

    public void addAllDailyDisciplines(List<DailyDiscipline> dailyDisciplines) {
        this.dailyDisciplines.addAll(dailyDisciplines);
        dailyDisciplines.forEach(dailyDiscipline -> {
            dailyDiscipline.setCompetency(this);
        });
    }

    public void removeDailyDiscipline(DailyDiscipline dailyDiscipline) {
        this.dailyDisciplines.remove(dailyDiscipline);
        dailyDiscipline.setCompetency(null);
    }

    public void removeAllDailyDisciplines(List<DailyDiscipline> dailyDisciplines) {
        this.dailyDisciplines.clear();
        dailyDisciplines.forEach(dailyDiscipline -> {
            dailyDiscipline.setCompetency(null);
        });
    }
}
