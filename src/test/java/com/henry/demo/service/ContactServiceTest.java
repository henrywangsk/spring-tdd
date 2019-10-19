package com.henry.demo.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.demo.data.repos.CustomerContactRepository;
import com.henry.demo.domain.CustomerContact;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {ContactServiceImpl.class})
public class ContactServiceTest {
  
  @Autowired
  private ContactService contactService;
  
  @MockBean
  private CustomerContactRepository customerContactRepository;
  
  private static Long id = Long.valueOf(111);
  private static String firstName = "Jenny";
  private static String email = "jenny@test.com";
  private static CustomerContact mockedContact;
  
  @BeforeClass
  public static void setUp() {
    mockedContact = new CustomerContact();
    mockedContact.setId(id);
    mockedContact.setFirstName(firstName);
    mockedContact.setEmail(email);
  }

  @Test
  public final void addContact_shouldReturnCreatedContact() {
    when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(mockedContact);
    
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName(firstName);
    aContact.setEmail(email);
    Optional<CustomerContact> createdContact = contactService.add(aContact);
    
    assertTrue(createdContact.isPresent());
    assertThat(createdContact.get(), is(equalTo(mockedContact)));
  }
  
  @Test
  public final void addContact_withoutEmailShouldReturnEmpty() {
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName(firstName);
    Optional<CustomerContact> createdContact = contactService.add(aContact);
    
    assertFalse(createdContact.isPresent());
  }
  
  @Test
  public final void findByEmail_shouldReturnFoundContact() {
    when(customerContactRepository.findByEmail(email)).thenReturn(mockedContact);
    
    Optional<CustomerContact> foundContact = contactService.findByEmail(email);
    
    assertTrue(foundContact.isPresent());
    assertThat(foundContact.get(), is(equalTo(mockedContact)));
  }

  @Test
  public final void findByEmail_shouldReturnEmptyWhenNotFound() {
    when(customerContactRepository.findByEmail(email)).thenReturn(null);
    
    Optional<CustomerContact> foundContact = contactService.findByEmail(email);
    
    assertFalse(foundContact.isPresent());
  }
}
