package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yoshimasa Tanabe
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("SELECT c FROM Customer c ORDER BY c.firstName, c.lastName")
  List<Customer> findAllOrderByName();

  @Query("SELECT c FROM Customer c ORDER BY c.firstName, c.lastName")
  Page<Customer> findAllOrderByName(Pageable pageable);

}
