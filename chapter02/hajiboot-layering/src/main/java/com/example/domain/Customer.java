package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yoshimasa Tanabe
 */
@Data
@AllArgsConstructor
public class Customer {

  private Integer id;
  private String firstName;
  private String lastName;

}
