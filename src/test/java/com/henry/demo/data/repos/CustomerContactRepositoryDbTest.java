package com.henry.demo.data.repos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.henry.demo.domain.CustomerContact;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DatabaseSetup("classpath:test-datasets.xml")
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class,
  DbUnitTestExecutionListener.class
})
public class CustomerContactRepositoryDbTest {
  @Autowired
  private CustomerContactRepository customerContactRepository;

  @Test
  public void findByEmail_shouldReturnContact() {
    CustomerContact foundContact = customerContactRepository.findByEmail("elaine@myemail.com");
    
    assertThat(foundContact.getEmail(), is(equalTo("elaine@myemail.com")));
  }
  
  @Test
  public void findByEmail_withNonExistEmailShouldReturnNull() {
    CustomerContact nullContact = customerContactRepository.findByEmail("nonexist@noexist.com");
    
    assertNull(nullContact);
  }
}
