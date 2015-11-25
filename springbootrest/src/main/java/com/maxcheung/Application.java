package com.maxcheung;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.maxcheung.domain.Customer;
import com.maxcheung.domain.CustomerRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}


	}

	@Bean
	CommandLineRunner init(CustomerRepository customerRepository) {
		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				Customer customer = new Customer();
				customer.setLastname("Bloggs");
				System.out.println("xxxmy beans method: ");
				customerRepository.save(customer);
			}
		};
	}
}
