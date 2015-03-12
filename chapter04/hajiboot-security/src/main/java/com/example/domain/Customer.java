package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Size(min = 1, max = 127)
  @Column(nullable = false)
  private String firstName;

  @NotNull
  @Size(min = 1, max = 127)
  @Column(nullable = false)
  private String lastName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = true, name = "username")
  private User user;

}
