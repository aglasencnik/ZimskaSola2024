package si.um.feri.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.vao.Subscription;

import java.util.List;

@ApplicationScoped
public class SubscriptionRepository implements PanacheRepository<Subscription> {

    public List<Subscription> getSubscriptionsByName(String name) {
        return list("name = ?1", name);
    }

}
