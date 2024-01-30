package si.um.feri.vao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.DeviceDTO;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private int commitmentLength;
    private int price;

    public Device(DeviceDTO dto) {
        updateFrom(dto);
    }

    public void updateFrom(DeviceDTO dto) {
        setTitle(dto.title());
        setDescription(dto.description());
        setCommitmentLength(dto.commitmentLength());
        setPrice(dto.price());
    }

    public DeviceDTO toDTO() {
        return new DeviceDTO(id, title, description, commitmentLength, price);
    }

}
