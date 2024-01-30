package si.um.feri.dto;

public record DeviceDTO(
        Long id,
        String title,
        String description,
        int commitmentLength,
        int price
) {
}
