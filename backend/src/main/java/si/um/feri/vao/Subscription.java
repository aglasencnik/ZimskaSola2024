package si.um.feri.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.SubscriptionDTO;

@Entity
@Data
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int price;

    public Subscription(SubscriptionDTO dto) {
        updateFrom(dto);
    }

    public void updateFrom(SubscriptionDTO dto) {
        setName(dto.name());
        setPrice(dto.price());
    }

    public SubscriptionDTO toDTO() {
        return new SubscriptionDTO(id, name, price);
    }

}
