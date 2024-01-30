package si.um.feri;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.dao.DeviceRepository;
import si.um.feri.dao.SubscriptionRepository;

@QuarkusTest
public class RestScenarioTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    DeviceRepository deviceRepository;

    @Inject
    SubscriptionRepository subscriptionRepository;

}
