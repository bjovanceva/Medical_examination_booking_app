package dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReserveRequestDto(
    LocalDate date,
    LocalTime time
) {
        }
