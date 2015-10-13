package com.maxcheung.memberenrolment;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;


public class MemberEnrolmentTest {

	// with type declaration
	// MathOperation addition = (BigDecimal a, BigDecimal b) -> a + b;

	private MemberEnrolment memberEnrolment;


	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("memberenrolment/ME_EMP100008015_240113145431059.xml").getFile());
		System.out.println(file.getAbsolutePath());

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(MemberEnrolment.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			memberEnrolment = (MemberEnrolment) jaxbUnmarshaller.unmarshal(file);
			System.out.println(memberEnrolment);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Test
	public void testFilter() {

		// @formatter:off

		// SELECT * FROM memberenrolment WHERE group = 'Group1';
		List<com.maxcheung.memberenrolment.DetailRecord> group1 = memberEnrolment.getDetailRecord().stream()
				.filter(e -> e.getGroup().equals("Group1"))
				.collect(Collectors.toList());
		assertEquals(25, group1.size());

		// SELECT * FROM memberenrolment WHERE group = 'Group2';
		List<com.maxcheung.memberenrolment.DetailRecord> group2 = memberEnrolment.getDetailRecord().stream()
				.filter(e -> e.getGroup().equals("Group2"))
				.collect(Collectors.toList());
		assertEquals(1, group2.size());

		// @formatter:on
	}

	
}
