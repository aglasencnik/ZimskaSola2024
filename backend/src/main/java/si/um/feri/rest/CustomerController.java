package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.dto.CustomerDTO;
import si.um.feri.vao.Customer;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private static final Logger log = Logger.getLogger(CustomerController.class.getName());

    @Inject
    CustomerRepository customerRepository;

    @GET
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.listAll().stream()
                .map(Customer::toDTO)
                .collect(Collectors.toList());
    }
}
