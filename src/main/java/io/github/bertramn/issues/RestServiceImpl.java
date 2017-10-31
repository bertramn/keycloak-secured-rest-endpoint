package io.github.bertramn.issues;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
public class RestServiceImpl implements RestService {

  public Response hello(String name) throws Exception {
    return Response.ok(new Message("Hi unsecured " + name)).build();
  }

  public Response helloSecured(String name) throws Exception {
    return Response.ok(new Message("Hi secured " + name)).build();
  }

}
