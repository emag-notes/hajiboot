package com.example.api;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author tanabe
 */
@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

  @Autowired
  CustomerService customerService;

  @RequestMapping(method = RequestMethod.GET)
  Page<Customer> get(@PageableDefault Pageable pageable) {
    return customerService.findAll(pageable);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  Customer get(@PathVariable Integer id) {
    return customerService.findOne(id);
  }

  @RequestMapping(method = RequestMethod.POST)
  ResponseEntity<Customer> post(@Validated  @RequestBody Customer customer, UriComponentsBuilder uriBuilder) {
    Customer created = customerService.create(customer);
    URI location = uriBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(location);
    return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  Customer post(@PathVariable Integer id, @Validated @RequestBody Customer customer) {
    customer.setId(id);
    return customerService.update(customer);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable Integer id) {
    customerService.delete(id);
  }

}
