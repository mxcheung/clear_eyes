package com.maxcheung.contribution.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

import com.maxcheung.contribution.ContributionSchedule;
import com.maxcheung.contribution.translate.ContributionTranslator;
import com.maxcheung.employer.domain.Contribution;
import com.maxcheung.payment.builder.ContributionPaymentBuilder;
import com.maxcheung.payment.domain.ContributionPayment;

public class ContributionPaymentBuilderTest {

	// with type declaration
	// MathOperation addition = (BigDecimal a, BigDecimal b) -> a + b;

	private ContributionSchedule contributionSchedule;
	private List<Contribution> contributions;

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

		
			assertNotNull(contributionSchedule);

			assertEquals(15, contributionSchedule.getDetailRecord().size());
			contributions = ContributionTranslator.from(contributionSchedule);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testContributionPaymentBuilder() {
	

		List<ContributionPayment> contributionPayments = new ArrayList<ContributionPayment>();
		
		ContributionPaymentBuilder contributionPaymentBuilder = new ContributionPaymentBuilder();
		for (Contribution contribution : contributions) {
			contributionPaymentBuilder.copyFrom(contribution);
			ContributionPayment contributionPayment = contributionPaymentBuilder.build();
			contributionPayments.add(contributionPayment);
		}

		assertEquals(15, contributionPayments.size());
		
	}

}
