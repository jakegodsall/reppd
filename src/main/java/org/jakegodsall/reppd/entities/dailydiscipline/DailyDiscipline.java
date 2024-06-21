package org.jakegodsall.reppd.entities.dailydiscipline;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.BaseEntity;
import org.jakegodsall.reppd.entities.Competency;
import lombok.*;
import org.jakegodsall.reppd.entities.DailyLog;
import org.jakegodsall.reppd.entities.LogDetails;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline_type", discriminatorType = DiscriminatorType.STRING)
public abstract class DailyDiscipline extends BaseEntity {

    private String title;
    private String description;

    // Minimum values for this discipline
    @Embedded
    private LogDetails logDetails;

    @ManyToOne
    @JoinColumn(name = "competency_id")
    private Competency competency;

    @OneToMany(mappedBy = "dailyDiscipline", cascade = CascadeType.ALL)
    private Set<DailyLog> dailyLogs;
}
