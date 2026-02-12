package dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TermRequestDto(
        String doctorUsername,
        LocalDate date,
        LocalTime time
) {
}

