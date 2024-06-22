package org.jakegodsall.reppd.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class DailyDiscipline extends BaseEntity {

    private String title;
    private String description;
    private Status status;
    private Long minimumValue;

    @ManyToOne
    @JoinColumn(name = "competency_id")
    private Competency competency;

    @Builder.Default
    @OneToMany(mappedBy = "dailyDiscipline", cascade = CascadeType.ALL)
    private Set<DailyLog> dailyLogs = new HashSet<>();

    public void addCompetency(Competency competency) {
        this.competency = competency;
        competency.getDailyDisciplines().add(this);
    }

    public void removeCompetency(Competency competency) {
        this.competency = null;
        competency.getDailyDisciplines().remove(this);
    }
}
