package si.um.feri.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.CustomerDTO;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDateTime created = LocalDateTime.now();

    public Customer(CustomerDTO dto) {
        setFirstName(dto.firstName());
        setLastName(dto.lastName());
    }

    public void updateFrom(CustomerDTO dto) {
        setFirstName(dto.firstName());
        setLastName(dto.lastName());
    }

    public CustomerDTO toDTO() {
        return new CustomerDTO(id, firstName, lastName);
    }
}
