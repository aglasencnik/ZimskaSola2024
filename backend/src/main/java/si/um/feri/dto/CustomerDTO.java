package si.um.feri.dto;

import si.um.feri.vao.Device;
import si.um.feri.vao.Subscription;

import java.util.List;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName,
        List<Device> devices,
        List<Subscription> subscriptions
) { }
