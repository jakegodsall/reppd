package org.jakegodsall.reppd.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseDto {
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
