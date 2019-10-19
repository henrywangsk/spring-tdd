package com.henry.demo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.henry.demo.domain.CustomerContact;
import com.henry.demo.service.ContactService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ContactControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ContactService contactService;

  @Test
  public void addContact_shouldSucceed() throws Exception {
    // setup mock Contact returned the mock service component
    CustomerContact mockCustomerContact = new CustomerContact();
    mockCustomerContact.setFirstName("Fred");

    when(contactService.add(any(CustomerContact.class))).thenReturn(Optional.of(mockCustomerContact));

    // simulate the form bean that would POST from the web page
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName("Fred");
    aContact.setEmail("fredj@myemail.com");

    mockMvc.perform(post("/addContact").flashAttr("contact", aContact))
      .andExpect(status().isOk())
      .andReturn();
  }

  @Test
  public void addContact_shouldRedirect() throws Exception {
    when(contactService.add(any(CustomerContact.class))).thenReturn(Optional.empty());

    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName("Fred");

    mockMvc.perform(post("/addContact").flashAttr("contact", aContact))
      .andExpect(status().is(302))
      .andReturn();
  }
}
