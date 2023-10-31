package com.lloydsbanking;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class GreetingResource {

    Logger logger = Logger.getLogger(GreetingResource.class);

    @Inject
    @RestClient
    DelayService delayService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/timeout/success")
    public Result testTimeOutSuccess() {
        Result result = delayService.callHealthyService();
        result.setStatus("Success");
        return result;
        //return "Hello from RESTEasy Reactive - Server 1";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/timeout/failure")
    @Timeout(250)
    public Result testTimeOutFailure() {
        Result result = delayService.callDelayService();
        return result;
        //return "Hello from RESTEasy Reactive - Server 1";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fallback")
    @Timeout(250)
    @Fallback(fallbackMethod = "callHealthy")
    public Result testFallback() {
        logger.info("Inside Fall back Method########");
        Result result = delayService.callDelayService();
        result.setStatus("FallBack");
        return result;
        //return "Hello from RESTEasy Reactive - Server 1";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/retry")
    @Retry(maxRetries = 2)
    public Result testRetry(){
        Result result = delayService.call50PcFailService();
        result.setStatus("Success");
        return result;
        //return "Hello from RESTEasy Reactive - Server 1";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/noretry")
    public Result testNoRetry(){
        Result result = delayService.call50PcFailService();
        result.setStatus("Success");
        return result;
        //return "Hello from RESTEasy Reactive - Server 1";
    }

    public Result callHealthy() {
        logger.info("Inside Fall back Method########");
        Result result = delayService.callHealthyService();
        result.setStatus("FallBack");
        return result;
        //return "Hello from RESTEasy Reactive - Server 1";
    }

}
