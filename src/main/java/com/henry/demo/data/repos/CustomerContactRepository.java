package com.henry.demo.data.repos;

import org.springframework.data.repository.CrudRepository;

import com.henry.demo.domain.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

  CustomerContact findByEmail(String email);

}
