package si.um.feri.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.vao.Device;

import java.util.List;

@ApplicationScoped
public class DeviceRepository implements PanacheRepository<Device> {

    public List<Device> getDevicesByName(String name) {
        return list("name = ?1", name);
    }

}
