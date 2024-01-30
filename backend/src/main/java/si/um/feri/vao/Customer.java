package si.um.feri.vao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.CustomerDTO;
import si.um.feri.dto.PostCustomerDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_device",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    private List<Device> devices = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_subscription",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private List<Subscription> subscriptions = new ArrayList<>();


    public Customer(PostCustomerDTO dto) {
        updateFrom(dto);
    }

    public void updateFrom(PostCustomerDTO dto) {
        setFirstName(dto.firstName());
        setLastName(dto.lastName());
    }

    public CustomerDTO toDTO() {
        return new CustomerDTO(id, firstName, lastName, devices, subscriptions);
    }

}
