package si.um.feri;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.dao.DeviceRepository;

@QuarkusTest
public class DeviceRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    DeviceRepository deviceRepository;

}
