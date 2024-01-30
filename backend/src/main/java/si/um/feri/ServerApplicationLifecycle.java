package si.um.feri;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.dao.DeviceRepository;
import si.um.feri.dao.SubscriptionRepository;
import si.um.feri.vao.Customer;
import si.um.feri.vao.Device;
import si.um.feri.vao.Subscription;

import java.util.logging.Logger;

@ApplicationScoped
public class ServerApplicationLifecycle {

    private static final Logger log = Logger.getLogger(ServerApplicationLifecycle.class.getName());

    @Inject
    CustomerRepository customerRepository;

    @Inject
    DeviceRepository deviceRepository;

    @Inject
    SubscriptionRepository subscriptionRepository;

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

        // Customers
        Customer c1 = new Customer();
        c1.setFirstName("Amadej");
        c1.setLastName("Glasenčnik");
        customerRepository.persist(c1);

        Customer c2 = new Customer();
        c2.setFirstName("Sara");
        c2.setLastName("Toman");
        customerRepository.persist(c2);

        Customer c3 = new Customer();
        c3.setFirstName("Matic");
        c3.setLastName("Jurač");
        customerRepository.persist(c3);

        Customer c4 = new Customer();
        c4.setFirstName("Gal");
        c4.setLastName("Povsod");
        customerRepository.persist(c4);

        Customer c5 = new Customer();
        c5.setFirstName("Janez");
        c5.setLastName("Novak");
        customerRepository.persist(c5);

        // Devices
        Device d1 = new Device();
        d1.setTitle("Pametni telefon");
        d1.setDescription("Visokokakovostni pametni telefon");
        d1.setCommitmentLength(24);
        d1.setPrice(799);
        deviceRepository.persist(d1);

        Device d2 = new Device();
        d2.setTitle("Tablični računalnik");
        d2.setDescription("Tablica z visoko ločljivostjo");
        d2.setCommitmentLength(36);
        d2.setPrice(499);
        deviceRepository.persist(d2);

        Device d3 = new Device();
        d3.setTitle("Prenosni računalnik");
        d3.setDescription("Laptop za profesionalno uporabo");
        d3.setCommitmentLength(12);
        d3.setPrice(1099);
        deviceRepository.persist(d3);

        Device d4 = new Device();
        d4.setTitle("Televizija");
        d4.setDescription("4K pametna televizija");
        d4.setCommitmentLength(24);
        d4.setPrice(1299);
        deviceRepository.persist(d4);

        Device d5 = new Device();
        d5.setTitle("Gaming konzola");
        d5.setDescription("Visoko zmogljiva igralna konzola");
        d5.setCommitmentLength(24);
        d5.setPrice(499);
        deviceRepository.persist(d5);

        // Subscriptions
        Subscription s1 = new Subscription();
        s1.setName("Osnovni paket");
        s1.setPrice(15);
        subscriptionRepository.persist(s1);

        Subscription s2 = new Subscription();
        s2.setName("Napredni paket");
        s2.setPrice(30);
        subscriptionRepository.persist(s2);

        Subscription s3 = new Subscription();
        s3.setName("Poslovni paket");
        s3.setPrice(45);
        subscriptionRepository.persist(s3);

        Subscription s4 = new Subscription();
        s4.setName("Starter paket");
        s4.setPrice(10);
        subscriptionRepository.persist(s4);

        Subscription s5 = new Subscription();
        s5.setName("Premium paket");
        s5.setPrice(50);
        subscriptionRepository.persist(s5);
    }
}
