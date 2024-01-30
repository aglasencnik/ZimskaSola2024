package si.um.feri;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.dao.SubscriptionRepository;

@QuarkusTest
public class SubscriptionRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    SubscriptionRepository subscriptionRepository;

}
