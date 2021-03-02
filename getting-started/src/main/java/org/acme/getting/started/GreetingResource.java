package org.acme.getting.started;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @Inject
    BugzillaService bugzillaService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/request/{id}")
    public String request(@PathParam String id) {
        System.out.println("requesting id : " + id);
        Map<String, Object> result = bugzillaService.getIssue(id);

        // just print some Bugziila issue metadata.
        for (String key : result.keySet()){
            System.out.println(key + result.get(key));
        }

        return result.keySet().toString();
    }
}