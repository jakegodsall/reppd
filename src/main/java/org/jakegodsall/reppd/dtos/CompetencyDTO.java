package org.jakegodsall.reppd.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jakegodsall.reppd.entities.enums.Status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompetencyDTO extends BaseDto {

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

}
