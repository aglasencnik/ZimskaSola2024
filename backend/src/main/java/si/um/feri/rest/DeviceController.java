package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.dao.DeviceRepository;
import si.um.feri.dto.DeviceDTO;
import si.um.feri.vao.Device;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceController {

    private static final Logger log = Logger.getLogger(DeviceController.class.getName());

    @Inject
    DeviceRepository deviceRepository;

    @GET
    public Response getAllDevices() {
        return Response.ok(deviceRepository.listAll().stream()
                .map(Device::toDTO)
                .collect(Collectors.toList())).build();
    }

    @GET
    @Path("/{id}")
    public Response getDeviceById(@PathParam("id") Long id) {
        Device device = deviceRepository.findById(id);
        if (device == null) {
            log.info("Device with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Device not found").build();
        }
        return Response.ok(device.toDTO()).build();
    }

    @GET
    @Path("/name")
    public Response getDevicesByName(@QueryParam("name") String name) {
        List<DeviceDTO> deviceDTOs = deviceRepository.getDevicesByName(name).stream()
                .map(Device::toDTO)
                .collect(Collectors.toList());
        return Response.ok(deviceDTOs).build();
    }

    @POST
    @Transactional
    public Response createDevice(DeviceDTO deviceDTO) {
        Device device = new Device(deviceDTO);
        deviceRepository.persist(device);
        return Response.ok(device.toDTO()).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDevice(@PathParam("id") Long id, DeviceDTO deviceDTO) {
        Device device = deviceRepository.findById(id);
        if (device == null) {
            log.info("Device with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Device not found").build();
        }
        device.updateFrom(deviceDTO);
        deviceRepository.persist(device);
        return Response.ok(device.toDTO()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDevice(@PathParam("id") Long id) {
        Device device = deviceRepository.findById(id);
        if (device == null) {
            log.info("Device with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Device not found").build();
        }
        deviceRepository.delete(device);
        return Response.ok().build();
    }

}
