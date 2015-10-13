package com.maxcheung.refund;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class RefundContextTest extends XMLTestCase {

	private RefundContext expectRefundContext;

	private String expectedXML;
	private ClassLoader classLoader = getClass().getClassLoader();

	private RefundContext refundContext;
	private List<RefundEvent> refundEvents;
	private List<RefundSummary> refundSummarys;

	@Before
	public void setUp() throws IOException {

		// JAXBContext jaxbContext;
		// try {
		// jaxbContext = JAXBContext.newInstance(RefundContext.class);
		// Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		// expectRefundContext = (RefundContext)
		// jaxbUnmarshaller.unmarshal(file);
		// System.out.println(expectRefundContext);
		// } catch (JAXBException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		XMLUnit.setIgnoreWhitespace(true); // ignore whitespace differences
		refundContext = new RefundContext();
		refundEvents = refundContext.getRefundEvent();
		refundSummarys = refundContext.getRefundSummary();
	}

	@Test
	public void testScenario1() throws JAXBException, SAXException, IOException {

		File file = new File(classLoader.getResource("refundContext/RF_scenario1.xml").getFile());
		expectedXML = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

		refundEvents.add(createRefundEvent("1", "CTR1", "E1", null, null, 1200));
		refundEvents.add(createRefundEvent("2", "CTR1", "E2", null, null, 1000));
		refundEvents.add(createRefundEvent("3", "CTR1", "E3", null, null, 800));

		// group by employer
		Map<String, List<RefundEvent>> employerRefundEvents = refundEvents.stream()
				.collect(Collectors.groupingBy(RefundEvent::getEmployer));

		employerRefundEvents.forEach((k, v) -> {
			refundSummarys.add(derivedRefundSummary(k, v));
		});

		assertXMLEqual(expectedXML, getRefundContextXMLString(refundContext));

	}

	@Test
	public void testScenario2() throws JAXBException, SAXException, IOException {

		File file = new File(classLoader.getResource("refundContext/RF_scenario2.xml").getFile());
		expectedXML = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

		refundEvents.add(createRefundEvent("1", "CTR1", "E1", "M1", null, 200));
		refundEvents.add(createRefundEvent("2", "CTR1", "E1", "M2", null, 200));
		refundEvents.add(createRefundEvent("3", "CTR1", "E1", "M3", null, 400));
		refundEvents.add(createRefundEvent("4", "CTR1", "E1", "M4", null, 400));
		refundEvents.add(createRefundEvent("5", "CTR1", "E2", "M5", null, 500));
		refundEvents.add(createRefundEvent("6", "CTR1", "E2", "M6", null, 500));

		// group by employer

		// @formatter:off
		refundEvents.stream()
			.collect(Collectors.groupingBy(RefundEvent::getEmployer))
			.forEach((k, v) -> {refundSummarys.add(derivedRefundSummary(k, v));	});
		// @formatter:on

		assertXMLEqual(expectedXML, getRefundContextXMLString(refundContext));

	}

	@Test
	public void testScenario3() throws JAXBException, SAXException, IOException {

		File file = new File(classLoader.getResource("refundContext/RF_scenario3.xml").getFile());
		expectedXML = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

		refundEvents.add(createRefundEvent("1", "CTR1", "E1", "M1", "SS", 200));
		refundEvents.add(createRefundEvent("2", "CTR1", "E1", "M1", "MV", 150));
		refundEvents.add(createRefundEvent("3", "CTR1", "E1", "M2", "DB", 150));
		refundEvents.add(createRefundEvent("4", "CTR1", "E2", "M3", "SS", 400));
		refundEvents.add(createRefundEvent("5", "CTR1", "E2", "M4", "SS", 165));

		// group by employer

		// @formatter:off
		refundEvents.stream()
			.collect(Collectors.groupingBy(RefundEvent::getEmployer))
			.forEach((k, v) -> {refundSummarys.add(derivedRefundSummary(k, v));	});
		// @formatter:on

		assertXMLEqual(expectedXML, getRefundContextXMLString(refundContext));

	}

	@Test
	public void testScenarioWithStats() throws JAXBException, SAXException, IOException {

		File file = new File(classLoader.getResource("refundContext/RF_scenario_stats.xml").getFile());
		expectedXML = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

		refundEvents.add(createRefundEvent("1", "CTR1", "E1", "M1", "SS", 200));
		refundEvents.add(createRefundEvent("2", "CTR1", "E1", "M1", "MV", 150));
		refundEvents.add(createRefundEvent("3", "CTR1", "E1", "M2", null, 150));
		refundEvents.add(createRefundEvent("4", "CTR1", "E2", null, null, 400));
		refundEvents.add(createRefundEvent("5", "CTR1", "E2", "M4", "SS", 165));

		// group by employer

		// @formatter:off
		Map<String, List<RefundEvent>> employerRefundEvents = refundEvents.stream().collect(Collectors.groupingBy(RefundEvent::getEmployer));
		
		employerRefundEvents.forEach((k, v) -> {
			refundSummarys.add(derivedRefundSummaryStats(k, v));
		});
		
		// @formatter:on

		assertXMLEqual(expectedXML, getRefundContextXMLString(refundContext));

	}

	// employerRefundEvents ==> EmployerSummary
	Function<com.maxcheung.memberenrolment.DetailRecord, com.maxcheung.contribution.DetailRecord> memberToContribution = new Function<com.maxcheung.memberenrolment.DetailRecord, com.maxcheung.contribution.DetailRecord>() {
		public com.maxcheung.contribution.DetailRecord apply(com.maxcheung.memberenrolment.DetailRecord t) {
			com.maxcheung.contribution.DetailRecord contDetail = new com.maxcheung.contribution.DetailRecord();
			contDetail.setNINumber(t.getNINumber());
			contDetail.setSurname(t.getSurname());
			return contDetail;
		}
	};

	private String getRefundContextXMLString(RefundContext refundContext) throws JAXBException, PropertyException {
		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(RefundContext.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(refundContext, sw);
		jaxbMarshaller.marshal(refundContext, System.out);
		return sw.toString();
	}

	private RefundSummary derivedRefundSummary(String employer, List<RefundEvent> refundEvents) {
		RefundSummary refundSummary = new RefundSummary();
		// @formatter:off
		 BigDecimal totalAmount = refundEvents.stream()
				 .map(x -> x.getContribAmount())
				 .reduce((a,b) -> a.add(b))
				 .get();
		 // @formatter:on
		refundSummary.setEmployer(employer);
		refundSummary.setContribAmount(totalAmount);
		return refundSummary;
	}

	private RefundSummary derivedRefundSummaryStats(String employer, List<RefundEvent> refundEvents) {
		RefundSummary refundSummary = new RefundSummary();
		BigDecimal totalAmount = BigDecimal.ZERO;
		int totalItems = 0;
		int employerItems = 0;
		int memberItems = 0; 
		int memberContribItems = 0;

		
		// determine
		//   totalAmount
		//   no of items
		//   items by category
		for (RefundEvent refundEvent : refundEvents) {
			totalAmount = totalAmount.add(refundEvent.getContribAmount());
			if (refundEvent.getContribType() != null) {
				memberContribItems++;
			} else if (refundEvent.getMember() != null) {
				memberItems++;
			} else if (refundEvent.getEmployer() != null) {
				employerItems++;
			}
			totalItems++;
		}

		refundSummary.setEmployer(employer);
		refundSummary.setContribAmount(totalAmount);
		refundSummary.setTotalItems(BigInteger.valueOf(totalItems));
		refundSummary.setEmployerItems(BigInteger.valueOf(employerItems));
		refundSummary.setMemberItems(BigInteger.valueOf(memberItems));
		refundSummary.setMemberContribItems(BigInteger.valueOf(memberContribItems));
		return refundSummary;
	}

	private RefundEvent createRefundEvent(String strEvent, String strCTR, String strEmployer, String strMember,
			String strContribType, int contribAmount) {
		RefundEvent refundEvent = new RefundEvent();
		refundEvent.setEvent(strEvent);
		refundEvent.setCTR(strCTR);
		refundEvent.setEmployer(strEmployer);
		refundEvent.setMember(strMember);
		refundEvent.setContribType(strContribType);
		refundEvent.setContribAmount(new BigDecimal(contribAmount));
		return refundEvent;
	}

}
