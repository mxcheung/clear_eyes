package com.maxcheung.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.maxcheung.Application;
import com.maxcheung.domain.Customer;
import com.maxcheung.domain.CustomerRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Max Cheung
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CustomerRepositoryControllerTest {
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private String userName = "bdussault";

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	private List<Customer> customerList = new ArrayList<>();
	private Customer customer1;
	private Customer customer2;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		this.customerRepository.deleteAllInBatch();

		customer1 = new Customer();
		customer1.setFirstname("john");
		customer1.setLastname("jones");

		customer2 = new Customer();
		customer2.setFirstname("steve");
		customer2.setLastname("smith");
		customerList.add(customer1);
		customerList.add(customer2);
		this.customer1 = customerRepository.save(customer1);
		this.customer2 = customerRepository.save(customer2);
	}
	
	
	 @Test
	    public void customerNotFound() throws Exception {
		 customer1.setLastname("Xyz");
	        mockMvc.perform(get("/v1/springboot/customer/fetchbyname")
	        		.param("lastname", customer1.getLastname())
	                .contentType(contentType))
	                .andExpect(status().isNotFound());
	    }
	
	 @Test
	    public void readSingleCustomer() throws Exception {
	        mockMvc.perform(get("/v1/springboot/customer?id="
	                + this.customerList.get(0).getId()))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(contentType))
	                .andExpect(jsonPath("$.id", is(Math.toIntExact(this.customerList.get(0).getId()))))
	                .andExpect(jsonPath("$.firstname", is(this.customerList.get(0).getFirstname())))
	                .andExpect(jsonPath("$.lastname", is(this.customerList.get(0).getLastname())));
	    }

	@Test
	public void readCustomers() throws Exception {
		mockMvc.perform(get("/v1/springboot/customer/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(Math.toIntExact(this.customerList.get(0).getId()))))
				.andExpect(jsonPath("$[0].firstname", is(this.customerList.get(0).getFirstname())))
				.andExpect(jsonPath("$[0].lastname", is(this.customerList.get(0).getLastname())))
				.andExpect(jsonPath("$[1].id", is(Math.toIntExact(this.customerList.get(1).getId()))))
				.andExpect(jsonPath("$[1].firstname", is(this.customerList.get(1).getFirstname())))
				.andExpect(jsonPath("$[1].lastname", is(this.customerList.get(1).getLastname())));
	}

	@Test
	public void createCustomer() throws Exception {
		customer1 = new Customer();
		customer1.setFirstname("newbie");
		customer1.setLastname("jones");
		String bookmarkJson = json(customer1);
		this.mockMvc.perform(post("/v1/springboot/customer")
				.contentType(contentType).content(bookmarkJson))
		.andExpect(status().isAccepted());
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}