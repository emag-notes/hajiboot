package com.example.api;

import com.example.App;
import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({
  "server.port:0",
  "spring.datasource.url:jdbc:h2:mem:bookmark;DB_CLOSE_ON_EXIT=FALSE"
})
public class CustomerRestControllerIntegrationTest {

  @Autowired
  CustomerRepository customerRepository;

  @Value("${local.server.port}")
  int port;

  String apiEndpoint;

  RestTemplate restTemplate = new TestRestTemplate();

  Customer customer1;
  Customer customer2;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class Page<T> {
    private List<T> content;
    private int numberOfElements;
  }

  @Before
  public void setUp() throws Exception {
    customerRepository.deleteAll();

    customer1 = new Customer();
    customer1.setFirstName("Taro");
    customer1.setLastName("Yamada");
    customer2 = new Customer();
    customer2.setFirstName("Ichiro");
    customer2.setLastName("Suzuki");
    customerRepository.save(Arrays.asList(customer1, customer2));

    apiEndpoint = "http://localhost:" + port + "/api/customers";
  }

  @Test
  public void testGetCustomers() throws Exception {
    // Setup & Exercise
    ResponseEntity<Page<Customer>> response = getAllCustomers();
    // Verify
    Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
    Assert.assertThat(response.getBody().getNumberOfElements(), is(2));

    Customer c1 = response.getBody().getContent().get(0);
    Assert.assertThat(c1.getId(), is(customer2.getId()));
    Assert.assertThat(c1.getFirstName(), is(customer2.getFirstName()));
    Assert.assertThat(c1.getLastName(), is(customer2.getLastName()));

    Customer c2 = response.getBody().getContent().get(1);
    Assert.assertThat(c2.getId(), is(customer1.getId()));
    Assert.assertThat(c2.getFirstName(), is(customer1.getFirstName()));
    Assert.assertThat(c2.getLastName(), is(customer1.getLastName()));
  }

  @Test
  public void testPostCustomer() throws Exception {
    // Setup
    Customer customer3 = new Customer();
    customer3.setFirstName("Nobita");
    customer3.setLastName("Nobi");

    // Exercise
    ResponseEntity<Customer> response = restTemplate.exchange(apiEndpoint, HttpMethod.POST, new HttpEntity<>(customer3), Customer.class);

    // Verify
    Assert.assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

    Customer customer = response.getBody();
    Assert.assertThat(customer.getId(), is(notNullValue()));
    Assert.assertThat(customer.getFirstName(), is(customer3.getFirstName()));
    Assert.assertThat(customer.getLastName(), is(customer3.getLastName()));

    Assert.assertThat(getAllCustomers().getBody().getNumberOfElements(), is(3));
  }

  @Test
  public void testDeleteCustomer() throws Exception {
    // Setup & Exercise
    ResponseEntity<Void> response =
      restTemplate.exchange(
        apiEndpoint + "/{id}",
        HttpMethod.DELETE,
        null,
        Void.class,
        Collections.singletonMap("id", customer1.getId()));

    // Verify
    Assert.assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));

    Assert.assertThat(getAllCustomers().getBody().getNumberOfElements(), is(1));
  }

  private ResponseEntity<Page<Customer>> getAllCustomers() {
    return restTemplate
      .exchange(
        apiEndpoint,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<Page<Customer>>() {
        });
  }

}