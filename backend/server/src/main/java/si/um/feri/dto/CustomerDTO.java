package si.um.feri.dto;

import java.time.LocalDateTime;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName
) { }
