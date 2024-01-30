package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.dao.CustomerRepository;
import si.um.feri.dao.DeviceRepository;
import si.um.feri.dao.SubscriptionRepository;
import si.um.feri.dto.CustomerDTO;
import si.um.feri.dto.PostCustomerDTO;
import si.um.feri.vao.Customer;
import si.um.feri.vao.Device;
import si.um.feri.vao.Subscription;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private static final Logger log = Logger.getLogger(CustomerController.class.getName());

    @Inject
    CustomerRepository customerRepository;

    @Inject
    DeviceRepository deviceRepository;

    @Inject
    SubscriptionRepository subscriptionRepository;

    @GET
    public Response getAllCustomers() {
        return Response.ok(customerRepository.listAll().stream()
                .map(Customer::toDTO)
                .collect(Collectors.toList())).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            log.info("Customer with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }

        return Response.ok(customer.toDTO()).build();
    }

    @GET
    @Path("/name")
    public Response getCustomerByName(@QueryParam("firstname") String firstName, @QueryParam("lastname") String lastName) {
        List<CustomerDTO> customerDTOs = customerRepository.getCustomersByName(firstName, lastName).stream()
                .map(Customer::toDTO)
                .collect(Collectors.toList());

        return Response.ok(customerDTOs).build();
    }

    @POST
    @Transactional
    public Response addCustomer(PostCustomerDTO dto) {
        Customer customer = new Customer(dto);
        customerRepository.persist(customer);
        return Response.ok(customer.toDTO()).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(@PathParam("id") Long id, PostCustomerDTO dto) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            log.info("Customer with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }

        customer.updateFrom(dto);
        customerRepository.persist(customer);

        return Response.ok(customer.toDTO()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            log.info("Customer with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }

        customerRepository.delete(customer);

        return Response.ok().build();
    }

    @POST
    @Path("/{customerId}/devices/{deviceId}")
    @Transactional
    public Response addDeviceToCustomer(
            @PathParam("customerId") Long customerId,
            @PathParam("deviceId") Long deviceId) {
        Customer customer = customerRepository.findById(customerId);
        Device device = deviceRepository.findById(deviceId);

        if (customer == null || device == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer or Device not found").build();
        }

        customer.getDevices().add(device);
        customerRepository.persist(customer);

        return Response.ok(customer.toDTO()).build();
    }

    @DELETE
    @Path("/{customerId}/devices/{deviceId}")
    @Transactional
    public Response removeDeviceFromCustomer(
            @PathParam("customerId") Long customerId,
            @PathParam("deviceId") Long deviceId) {
        Customer customer = customerRepository.findById(customerId);
        Device device = deviceRepository.findById(deviceId);

        if (customer == null || device == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer or Device not found").build();
        }

        customer.getDevices().remove(device);
        customerRepository.persist(customer);

        return Response.ok(customer.toDTO()).build();
    }

    @POST
    @Path("/{customerId}/subscriptions/{subscriptionId}")
    @Transactional
    public Response addSubscriptionToCustomer(
            @PathParam("customerId") Long customerId,
            @PathParam("subscriptionId") Long subscriptionId) {
        Customer customer = customerRepository.findById(customerId);
        Subscription subscription = subscriptionRepository.findById(subscriptionId);

        if (customer == null || subscription == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer or Subscription not found").build();
        }

        customer.getSubscriptions().add(subscription);
        customerRepository.persist(customer);

        return Response.ok(customer.toDTO()).build();
    }

    @DELETE
    @Path("/{customerId}/subscriptions/{subscriptionId}")
    @Transactional
    public Response removeSubscriptionFromCustomer(
            @PathParam("customerId") Long customerId,
            @PathParam("subscriptionId") Long subscriptionId) {
        Customer customer = customerRepository.findById(customerId);
        Subscription subscription = subscriptionRepository.findById(subscriptionId);

        if (customer == null || subscription == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer or Subscription not found").build();
        }

        customer.getSubscriptions().remove(subscription);
        customerRepository.persist(customer);

        return Response.ok(customer.toDTO()).build();
    }

}
