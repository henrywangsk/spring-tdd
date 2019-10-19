package com.henry.demo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.demo.data.repos.CustomerContactRepository;
import com.henry.demo.domain.CustomerContact;

@Service
public class ContactsManagementServiceImpl implements ContactsManagementService {
  @Autowired
  private CustomerContactRepository customerContactRepository;

  @Override
  public Optional<CustomerContact> add(CustomerContact aContact) {
    CustomerContact saved = null;
    if (Objects.nonNull(aContact) && Objects.nonNull(aContact.getEmail())) {
      saved = customerContactRepository.save(aContact);
    }
    
    return Optional.ofNullable(saved);
  }

  @Override
  public Optional<CustomerContact> findByEmail(String email) {
    final CustomerContact foundContact = customerContactRepository.findByEmail(email);
    return Optional.ofNullable(foundContact);
  }

}
