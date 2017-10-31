package io.github.bertramn.issues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScans({
  @ComponentScan("org.keycloak")
})
public class RestApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }

  @Bean(name = "jsonProvider")
  public JacksonJsonProvider jsonProvider(final ObjectMapper mapper) {
    return new JacksonJsonProvider(mapper);
  }

}
