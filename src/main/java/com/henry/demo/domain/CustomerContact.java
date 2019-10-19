package com.henry.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="customer_contact")
@Data
public class CustomerContact {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String deliveryAddressLine1;
  private String deliveryAddressLine2;
  private String deliveryAddressCity;
  private String deliveryAddressState;
  private String deliveryAddressLZipCode;

}
