package si.um.feri.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.vao.Customer;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> getCustomersByName(String firstName, String lastName) {
        List<Customer> customers;

        if (firstName != null && lastName != null) {
            customers = list("firstName = ?1 and lastName = ?2", firstName, lastName);
        } else if (firstName != null) {
            customers = list("firstName = ?1", firstName);
        } else if (lastName != null) {
            customers = list("lastName = ?1", lastName);
        } else {
            customers = listAll();
        }

        return customers;
    }

}
