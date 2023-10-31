package com.lloydsbanking;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/testAPI")
@RegisterRestClient
public interface DelayService {
    @GET
    @Path("/delay500ms")
    public Result callDelayService();

    @GET
    @Path("/delay0ms")
    public Result callHealthyService();

    @GET
    @Path("/fail50pc")
    public Result call50PcFailService();
}