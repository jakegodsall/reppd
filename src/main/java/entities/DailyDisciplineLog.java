package entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class DailyDisciplineLog extends BaseEntity {

    private LocalDateTime date;
    private Long value;

}
