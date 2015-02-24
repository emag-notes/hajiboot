package com.example.repository;

import com.example.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yoshimasa Tanabe
 */
@Repository
public class CustomerRepository {

  private final ConcurrentHashMap<Integer, Customer> customerMap = new ConcurrentHashMap<>();

  public List<Customer> findAll() {
    return new ArrayList<>(customerMap.values());
  }

  public Customer findOne(Integer id) {
    return customerMap.get(id);
  }

  public Customer save(Customer customer) {
    return customerMap.put(customer.getId(), customer);
  }

  public void delete(Integer id) {
    customerMap.remove(id);
  }

}
