package com.henry.demo.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.demo.domain.CustomerContact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class ContactServiceIntegrationTest {
  @Autowired
  private ContactService contactService;
  
  @Test
  public void addContact_shouldReturnCreatedContact() {
    String firstName = "Jenny";
    String email = "jenny@test.com";
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName(firstName);
    aContact.setEmail(email);
    
    Optional<CustomerContact> createdContact = contactService.add(aContact);
    assertTrue(createdContact.isPresent());
    assertNotNull(createdContact.get().getId());
    assertThat(createdContact.get().getFirstName(), is(equalTo(firstName)));
    assertThat(createdContact.get().getEmail(), is(equalTo(email)));
  }
  
  @Test
  public void addContact_withoutEmailShouldReturnEmpty() {
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName("Jenny");
    
    Optional<CustomerContact> createdContact = contactService.add(aContact);
    assertFalse(createdContact.isPresent());
  }
}
