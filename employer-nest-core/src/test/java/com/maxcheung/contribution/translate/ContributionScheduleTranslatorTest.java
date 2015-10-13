package com.maxcheung.contribution.translate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import com.maxcheung.contribution.ContributionSchedule;
import com.maxcheung.contribution.DetailRecord;
import com.maxcheung.employer.domain.Contribution;

public class ContributionScheduleTranslatorTest {

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
		List<Contribution> contributions = ContributionTranslator.from(contributionSchedule);

		for (Contribution contribution : contributions) {
			System.out.print(contribution.getEmployerReference() + " : ");
			System.out.println(contribution.getSurname());

		}
		;

		assertEquals(15, contributions.size());
	}

}
