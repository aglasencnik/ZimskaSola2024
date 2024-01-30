package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.dao.SubscriptionRepository;
import si.um.feri.dto.SubscriptionDTO;
import si.um.feri.vao.Subscription;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionController {

    private static final Logger log = Logger.getLogger(SubscriptionController.class.getName());

    @Inject
    SubscriptionRepository subscriptionRepository;

    @GET
    public Response getAllDSubscription() {
        return Response.ok(subscriptionRepository.listAll().stream()
                .map(Subscription::toDTO)
                .collect(Collectors.toList())).build();
    }

    @GET
    @Path("/{id}")
    public Response getSubscriptionById(@PathParam("id") Long id) {
        Subscription subscription = subscriptionRepository.findById(id);
        if (subscription == null) {
            log.info("Subscription with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Subscription not found").build();
        }
        return Response.ok(subscription.toDTO()).build();
    }

    @GET
    @Path("/name")
    public Response getSubscriptionsByName(@QueryParam("name") String name) {
        List<SubscriptionDTO> subscriptionDTOs = subscriptionRepository.getSubscriptionsByName(name).stream()
                .map(Subscription::toDTO)
                .collect(Collectors.toList());
        return Response.ok(subscriptionDTOs).build();
    }

    @POST
    @Transactional
    public Response createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription(subscriptionDTO);
        subscriptionRepository.persist(subscription);
        return Response.ok(subscription.toDTO()).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateSubscription(@PathParam("id") Long id, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(id);
        if (subscription == null) {
            log.info("Subscription with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Subscription not found").build();
        }
        subscription.updateFrom(subscriptionDTO);
        subscriptionRepository.persist(subscription);
        return Response.ok(subscription.toDTO()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteSubscription(@PathParam("id") Long id) {
        Subscription subscription = subscriptionRepository.findById(id);
        if (subscription == null) {
            log.info("Subscription with id: " + id + " not found.");
            return Response.status(Response.Status.NOT_FOUND).entity("Subscription not found").build();
        }
        subscriptionRepository.delete(subscription);
        return Response.ok().build();
    }

}
