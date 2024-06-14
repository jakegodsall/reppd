package org.jakegodsall.reppd.entities;

import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@Entity
public class Goal extends BaseEntity {
    private String title;
    private String description;
    private Status status;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String smartDetail;
    private String measurableDetail;
    private String achievableDetail;
    private String relevantDetail;
    private String timeboundDetail;
}
