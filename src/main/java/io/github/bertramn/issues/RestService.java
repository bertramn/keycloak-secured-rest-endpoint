package io.github.bertramn.issues;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/")
@Service
public interface RestService {

  @GET
  @Path("/notsecured")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  Response hello(@QueryParam("name") String name) throws Exception;

  @GET
  @Path("/secured")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  Response helloSecured(@QueryParam("name") String name) throws Exception;

}
