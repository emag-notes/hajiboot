package com.example;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yoshimasa Tanabe
 */
@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public void run(String... args) throws Exception {
    Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
    System.out.println(created + " is created!");

    customerRepository.findAll()
      .forEach(System.out::println);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
