package si.um.feri;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import si.um.feri.dao.CustomerRepository;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

}
