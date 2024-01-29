package si.um.feri;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.vao.Customer;

import java.util.logging.Logger;

@ApplicationScoped
public class ServerApplicationLifecycle {

    private static final Logger log = Logger.getLogger(ServerApplicationLifecycle.class.getName());

    @Inject
    CustomerRepository customerRepository;

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        populateTestDataIfNotPresent();
    }

    @Transactional
    void populateTestDataIfNotPresent() {
        if (customerRepository.count() > 0) {
            log.info("Test data population skipped...");
            return;
        }

        Customer c1 = new Customer();
        c1.setFirstName("Amadej");
        c1.setLastName("Glasenčnik");
        customerRepository.persist(c1);
    }
}
