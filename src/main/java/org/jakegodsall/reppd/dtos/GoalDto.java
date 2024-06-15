package org.jakegodsall.reppd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GoalDto extends BaseDto {

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
