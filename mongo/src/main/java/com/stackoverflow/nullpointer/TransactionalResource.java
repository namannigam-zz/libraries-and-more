package com.stackoverflow.nullpointer;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/transact")
@Api(value = "transact", description = "Mongo Transactional Resources")
public class TransactionalResource {

    private static final Logger logger = LoggerFactory.getLogger(TransactionalResource.class);

    @POST
    @Timed(name = "create-doc")
    @ApiOperation(value = "Create document.")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.BAD_REQUEST_400, message = "Bad Request"),
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500, message = "Server Error")
    })
    public Response createCampaign(@ApiParam("campaignRequest") @NotNull Object campaignRequest) {
        return Response.status(HttpStatus.OK_200).entity(String.format("Created campaign for %s!", campaignRequest))
                .build();
    }
}