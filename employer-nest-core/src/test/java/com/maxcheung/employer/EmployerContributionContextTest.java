package com.maxcheung.employer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import com.maxcheung.employer.EmployerContributionContext.EmployerSummary;

public class EmployerContributionContextTest {


	private EmployerContributionContext employerContributionContext;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("employercontribution/employer-test1.xml").getFile());
		System.out.println(file.getAbsolutePath());

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(EmployerContributionContext.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			employerContributionContext = (EmployerContributionContext) jaxbUnmarshaller.unmarshal(file);
			System.out.println(employerContributionContext);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDefault() {
		assertNotNull(employerContributionContext);
		assertEquals(2, employerContributionContext.getEmployerSummary().size());

	}

	@Test
	public void testFilter() {

		List<EmployerSummary> empSummary = employerContributionContext.getEmployerSummary();
		assertEquals(1, getEmploySummaryList(empSummary, "Robinson").size());
		assertEquals(1, getEmploySummaryList(empSummary, "Brown").size());
		assertEquals(0, getEmploySummaryList(empSummary, "Unknown").size());
	}

	@Test
	public void testEmployerSummary() {

		List<EmployerSummary> empSummaryList = employerContributionContext.getEmployerSummary();
		EmployerSummary employerSummary;
		employerSummary = getEmploySummary(empSummaryList, "Robinson");
		assertNotNull(employerSummary);
		assertEquals("Robinson", employerSummary.getSurname());
		assertEquals("AB010109A", employerSummary.getNINumber());
		assertTrue(new BigDecimal("200").compareTo(employerSummary.getEmployerContribution()) == 0);
		assertTrue(new BigDecimal("160").compareTo(employerSummary.getMemberContribution()) == 0);

		employerSummary = getEmploySummary(empSummaryList, "Brown");
		assertNotNull(employerSummary);
		assertEquals("Brown", employerSummary.getSurname());
		assertEquals("AB010113A", employerSummary.getNINumber());
		assertTrue(new BigDecimal("210").compareTo(employerSummary.getEmployerContribution()) == 0);
		assertTrue(new BigDecimal("161").compareTo(employerSummary.getMemberContribution()) == 0);

		employerSummary = getEmploySummary(empSummaryList, "Unknown");
		assertNull(employerSummary);

	}
	
	
	@Test
	public void testCustomFilter() {
	
		List<EmployerSummary> empSummaryList = employerContributionContext.getEmployerSummary();
		List<EmployerSummary> empSummaryfilter = empSummaryList.stream()
				.filter(e -> e.getSurname().equals("Brown"))
				.filter(e -> e.getEmployerContribution().compareTo(new BigDecimal("210"))== 0    )
				.collect(Collectors.toList());
		assertEquals(1,empSummaryfilter.size());
		
	}

	

	// SELECT * FROM employer WHERE surname = 'Robinson';
	private List<EmployerSummary> getEmploySummaryList(List<EmployerSummary> empSummary, String surname) {
		// @formatter:off
		List<EmployerSummary> empSummaryfilter = empSummary.stream()
				.filter(e -> e.surname.equals(surname))
				.collect(Collectors.toList());
		return empSummaryfilter;
		// @formatter:on
	}

	// SELECT Top 1 * FROM employer WHERE surname = 'Robinson';
	private EmployerSummary getEmploySummary(List<EmployerSummary> empSummary, String surname) {
 		// @formatter:off
			List<EmployerSummary> empSummaryfilter = getEmploySummaryList(empSummary,surname);
			return empSummaryfilter.size() > 0 ? empSummaryfilter.get(0) : null;
			// @formatter:on
	}


	@Test
	public void testCustomFilterAndPrint() {
	
		List<EmployerSummary> empSummaryList = employerContributionContext.getEmployerSummary();
		List<EmployerSummary> empSummaryfilter = empSummaryList.stream()
				.filter(e -> e.getSurname().equals("Robinson"))
				.filter(e -> e.getMemberContribution().compareTo(new BigDecimal("160"))== 0    )
				.collect(Collectors.toList());
		assertEquals(1,empSummaryfilter.size());

		empSummaryfilter.stream().forEach(System.out::println);
		

		// Using lambda expression and functional operations
		empSummaryfilter.forEach((e) -> System.out.print(e.getSurname()+ "; "));
		 
		// Using double colon operator in Java 8
		empSummaryfilter.forEach(System.out::println);
		
		
	}




}
