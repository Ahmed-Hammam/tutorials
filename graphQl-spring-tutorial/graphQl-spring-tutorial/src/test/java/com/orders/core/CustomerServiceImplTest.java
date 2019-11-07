package com.orders.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.orders.core.entity.CustomerEntity;
import com.orders.core.repository.CustomersRepository;
import com.orders.core.service.CustomerServiceImpl;

public class CustomerServiceImplTest {

	@InjectMocks // used with the class we need to test
	private CustomerServiceImpl customerService;

	@Mock // used with any dependency in the class
	private CustomersRepository customerRepo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void insertCustomerTest() {
		CustomerEntity customer = CustomerEntity.builder().code(UUID.randomUUID().toString()).email("aa@mail.com")
				.firstName("firstName").lastName("lastName").build();
		customer.setId(1L);

		when(customerRepo.save(customer)).thenReturn(customer);
		CustomerEntity insertedCustomer = customerService.insert(customer);

		verify(customerRepo, times(1)).save(customer);
		assertEquals(insertedCustomer, customer);
	}

	@Test
	public void removeCustomer() {
		final String code = "abc";
		customerService.remove(code);
		verify(customerRepo, times(1)).deleteByCode(code);
	}

	// selectOneById test cases
	@Test
	public void retrieveCustomerByIdValidTest() {
		CustomerEntity customer = CustomerEntity.builder().code(UUID.randomUUID().toString()).email("aa@mail.com")
				.firstName("firstName").lastName("lastName").build();
		customer.setId(1L);

		when(customerRepo.getOne(1L)).thenReturn(customer);
		CustomerEntity insertedCustomer = customerService.selectOneById(1L).get();

		verify(customerRepo, times(1)).getOne(1L);
		assertEquals(insertedCustomer, customer);
	}

	@Test(expected = NullPointerException.class) // TODO convert to business exception
	public void retrieveCustomerByIdInvalidTest() {
		when(customerService.selectOneById(2L)).thenThrow(new NullPointerException("No customer found !"));

	}

	// selectByEntityCode test cases
	@Test
	public void retrieveCustomerByCodeValidTest() {
		final String code = "abc";
		Optional<CustomerEntity> optionalCustomer = Optional
				.of(CustomerEntity.builder().code(code).email("aa@mail.com").firstName("firstName")
				.lastName("lastName").build());

		when(customerRepo.findByCode(code)).thenReturn(optionalCustomer);
		CustomerEntity insertedCustomer = customerService.selectByEntityCode(code).get();

		verify(customerRepo, times(1)).findByCode(code);
		assertEquals(insertedCustomer.getCode(), code);
	}

	@Test(expected = NullPointerException.class) // TODO convert to business exception
	public void retrieveCustomerByCodeInvalidTest() {
		when(customerRepo.findByCode("xyz")).thenThrow(new NullPointerException("No customer found !"));
		customerService.selectByEntityCode("xyz").get();
	}
	

}
