package org.jakegodsall.reppd.dtos;

import org.jakegodsall.reppd.entities.enums.Status;

import java.time.LocalDateTime;

public class GoalDto extends BaseDto {
    private String title;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String smartDetail;
    private String measurableDetail;
    private String achievableDetail;
    private String relevantDetail;
    private String timeboundDetail;
}
