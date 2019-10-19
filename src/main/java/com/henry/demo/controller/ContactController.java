package com.henry.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.henry.demo.domain.CustomerContact;
import com.henry.demo.service.ContactService;

@Controller
public class ContactController {
  @Autowired
  private ContactService contactService;

  @RequestMapping(value= "/addContact", method=RequestMethod.POST)
  public String processAddContactSubmit(@ModelAttribute(name="contact") CustomerContact aContact) {
    Optional<CustomerContact> newContact = contactService.add(aContact);
    return newContact.isPresent() ? "/addContactForm" : "redirect:/showAddContact";
  }
  
  @RequestMapping(value= "/showAddContact", method=RequestMethod.GET)
  public String showAddContact() {
    
    // TODO: implement this
    
    return "/addContactForm";
  }
}
