package com.henry.demo.service;

import java.util.Optional;

import com.henry.demo.domain.CustomerContact;

public interface ContactsManagementService {

  Optional<CustomerContact> add(CustomerContact aContact);

  Optional<CustomerContact> findByEmail(String email);

}
