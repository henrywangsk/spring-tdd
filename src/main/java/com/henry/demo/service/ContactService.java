package com.henry.demo.service;

import java.util.Optional;

import com.henry.demo.domain.CustomerContact;

public interface ContactService {

  Optional<CustomerContact> add(CustomerContact aContact);

  Optional<CustomerContact> findByEmail(String email);

}
