package com.maxcheung.contribution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;


public class ContributionScheduleTest {

	// with type declaration
	// MathOperation addition = (BigDecimal a, BigDecimal b) -> a + b;

	private ContributionSchedule contributionSchedule;

	@Test
	public void test() {
		ContributionSchedule contributionSchedule = new ContributionSchedule();
		assertNotNull(contributionSchedule);
	}

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("contribution/CS_EMP100008015_13031418073465.xml").getFile());
		System.out.println(file.getAbsolutePath());

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ContributionSchedule.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			contributionSchedule = (ContributionSchedule) jaxbUnmarshaller.unmarshal(file);
			System.out.println(contributionSchedule);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testSummaryTotal() {

		assertNotNull(contributionSchedule);

		assertEquals(15, contributionSchedule.getDetailRecord().size());
		BigDecimal employerTotalContribution = BigDecimal.ZERO;
		BigDecimal memberTotalContribution = BigDecimal.ZERO;
		for (DetailRecord detailRecord : contributionSchedule.getDetailRecord()) {
			employerTotalContribution = employerTotalContribution.add(detailRecord.getEmployerContribution());
			memberTotalContribution = memberTotalContribution.add(detailRecord.getMemberContribution());
		}

		BigDecimal employerTotalContribution1 = contributionSchedule.getDetailRecord().stream()
				.map((empCont) -> empCont.getEmployerContribution()).reduce((x, y) -> x.add(y)).get();
		BigDecimal memberTotalContribution1 = contributionSchedule.getDetailRecord().parallelStream()
				.map((x) -> x.getMemberContribution()).reduce((x, y) -> x.add(y)).get();

		assertTrue(new BigDecimal("3000").compareTo(employerTotalContribution) == 0);
		assertTrue(new BigDecimal("3000").compareTo(employerTotalContribution1) == 0);
		assertTrue(new BigDecimal("2400").compareTo(memberTotalContribution) == 0);
		assertTrue(new BigDecimal("2400").compareTo(memberTotalContribution1) == 0);

	}

	@Test
	public void testTaxRate() {
		BigDecimal employerTotalContributionTax = contributionSchedule.getDetailRecord().stream()
				.map((empCont) -> empCont.getEmployerContribution().multiply(new BigDecimal("0.15")))
				.reduce((x, y) -> x.add(y)).get();

		assertTrue(new BigDecimal("450").compareTo(employerTotalContributionTax) == 0);
	}

	@Test
	public void testFilter() {

		// @formatter:off

		// SELECT SUM(employerContribution) FROM contributions WHERE surname = 'Xi';
		BigDecimal employerTotalContributionXi = contributionSchedule.getDetailRecord().stream()
				.filter(e -> e.getSurname().equals("Xi"))
				.map((empCont) -> empCont.getEmployerContribution())
				.reduce((x, y) -> x.add(y)).get();

		assertTrue(new BigDecimal("200").compareTo(employerTotalContributionXi) == 0);
		// @formatter:on
	}

}
