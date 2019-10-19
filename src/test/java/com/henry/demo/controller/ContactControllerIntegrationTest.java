package com.henry.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.henry.demo.domain.CustomerContact;

@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Transactional
  public final void addContact() throws Exception {
    // simulate the form bean that would POST from the web page
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName("Fred");
    aContact.setEmail("fredj@myemail.com");

    // simulate the form submit (POST)
    mockMvc.perform(post("/addContact").flashAttr("contact", aContact))
      .andExpect(status().isOk())
      .andReturn();
  }

  @Test
  public final void addContact_shouldRedirect() throws Exception {
    // simulate the form bean that would POST from the web page
    CustomerContact aContact = new CustomerContact();
    aContact.setFirstName("Fred");

    // simulate the form submit (POST)
    mockMvc.perform(post("/addContact").flashAttr("contact", aContact))
      .andExpect(status().is(302))
      .andReturn();
  }

}
