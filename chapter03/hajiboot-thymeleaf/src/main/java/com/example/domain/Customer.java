package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yoshimasa Tanabe
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue
  private Integer id;

  @NotNull
  @Size(min = 1, max = 127)
  @Column(nullable = false)
  private String firstName;

  @NotNull
  @Size(min = 1, max = 127)
  @Column(nullable = false)
  private String lastName;

}
