package com.maxcheung.potentialrefund;

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

import com.maxcheung.refund.PotentialRefundContext;
import com.maxcheung.refund.PotentialRefundContext.PotentialRefundContributions;
import com.maxcheung.refund.PotentialRefundContext.PotentialRefundPartyBankccounts;
import com.maxcheung.refund.PotentialRefundContext.PotentialRefundPayments;
import com.maxcheung.refund.PotentialRefundContribution;
import com.maxcheung.refund.PotentialRefundPartyBankccount;
import com.maxcheung.refund.PotentialRefundPayment;
import com.maxcheung.refund.PotentialRefundSummary;

public class PotentialRefundContextTest extends XMLTestCase {

	private PotentialRefundContext expectRefundContext;

	private String expectedXML;
	private ClassLoader classLoader = getClass().getClassLoader();

	private PotentialRefundContext potentRefundContext;

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
		potentRefundContext = new PotentialRefundContext();
		potentRefundContext.setPotentialRefundContributions(new PotentialRefundContributions());
		potentRefundContext.setPotentialRefundPartyBankccounts(new PotentialRefundPartyBankccounts());
		potentRefundContext.setPotentialRefundPayments(new PotentialRefundPayments());
		
	}

	@Test
	public void testScenario1() throws JAXBException, SAXException, IOException {

		potentRefundContext.setId("PRM1");
		PotentialRefundSummary potentialRefundSummary = new PotentialRefundSummary();
		potentialRefundSummary.setPrmId("PRM1");
		potentialRefundSummary.setCTSPId("CTP1");
		potentialRefundSummary.setOriginalCTR("CTR123");
		potentialRefundSummary.setRefundCTR("RefundCTR123");
		potentRefundContext.setPotentialRefundSummary(potentialRefundSummary);
		List<PotentialRefundContribution> contributions = potentRefundContext.getPotentialRefundContributions().getPotentialRefundContribution();
		contributions.add(createPotentialRefundContribution("PRM1","E1",null,null,BigDecimal.valueOf(100)));
		contributions.add(createPotentialRefundContribution("PRM1","E2","M1",null,BigDecimal.valueOf(100)));
		contributions.add(createPotentialRefundContribution("PRM1","E3","M1",null,BigDecimal.valueOf(50)));
		contributions.add(createPotentialRefundContribution("PRM1","E3","M2","GC",BigDecimal.valueOf(25)));
		contributions.add(createPotentialRefundContribution("PRM1","E3","M2","MV",BigDecimal.valueOf(25)));

		
		List<PotentialRefundPartyBankccount> contribBankAcounts = potentRefundContext.getPotentialRefundPartyBankccounts().getPotentialRefundPartyBankccount();
		contribBankAcounts.add(createPotentialRefundPartyBankAccount("PRM1","F1","Refund","123456","12345678","Fund"));
		contribBankAcounts.add(createPotentialRefundPartyBankAccount("PRM1","E1","Refund","123457","12345679","Employer1"));
		contribBankAcounts.add(createPotentialRefundPartyBankAccount("PRM1","E2","Refund","123458","12345680","Employer2"));
		contribBankAcounts.add(createPotentialRefundPartyBankAccount("PRM1","E3","Refund","123459","12345681","Employer3"));
		
		
		List<PotentialRefundPayment> contributionPayments = potentRefundContext.getPotentialRefundPayments().getPotentialRefundPayment();
		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1",null, BigDecimal.valueOf(100), "IN",contribBankAcounts.get(0)));
		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1","E1", BigDecimal.valueOf(-100), "OUT",contribBankAcounts.get(1)));
		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1","E2", BigDecimal.valueOf(-100), "OUT",contribBankAcounts.get(2)));
		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1","E3", BigDecimal.valueOf(-100), "OUT",contribBankAcounts.get(3)));

		
		//		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1",null, BigDecimal.valueOf(100), "IN",createPotentialRefundPartyBankAccount("PRM1","F1","Refund","123456","12345678","Fund")));
//		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1","E1", BigDecimal.valueOf(-100), "OUT",createPotentialRefundPartyBankAccount("PRM1","E1","Refund","123457","12345679","Employer1")));
//		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1","E2", BigDecimal.valueOf(-100), "OUT",createPotentialRefundPartyBankAccount("PRM1","E2","Refund","123458","12345680","Employer2")));
//		contributionPayments.add(createPotentialRefundPayment("PRM1", "F1","E3", BigDecimal.valueOf(-100), "OUT",createPotentialRefundPartyBankAccount("PRM1","E3","Refund","123459","12345681","Employer3")));
//

		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PotentialRefundContext.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(potentRefundContext, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private PotentialRefundPartyBankccount createPotentialRefundPartyBankAccount(String prmId, String entityType, String accountType, String bsb, String accountNo, String accountName) {
		PotentialRefundPartyBankccount	potentialRefundPartyBankccount = new PotentialRefundPartyBankccount();
		potentialRefundPartyBankccount.setPrmId(prmId);
		potentialRefundPartyBankccount.setEntityType(entityType);
		potentialRefundPartyBankccount.setAccountType(accountType);
		potentialRefundPartyBankccount.setBsb(bsb);
		potentialRefundPartyBankccount.setAccountNo(accountNo);
		potentialRefundPartyBankccount.setAccountName(accountName);
		
		return potentialRefundPartyBankccount;
	}

	private PotentialRefundContribution createPotentialRefundContribution(String prmId, String employer, String member, String contrib, BigDecimal contribAmount) {
		PotentialRefundContribution potentialRefundContribution = new PotentialRefundContribution();
		potentialRefundContribution.setPrmId(prmId);
		potentialRefundContribution.setEmployer(employer);
		potentialRefundContribution.setMember(member);
		potentialRefundContribution.setContrib(contrib);
		potentialRefundContribution.setContribAmount(contribAmount);
		return potentialRefundContribution;
	}

	private PotentialRefundPayment createPotentialRefundPayment(String prmId, String source, String dest, BigDecimal paymentAmt, String rpCode, PotentialRefundPartyBankccount potentialRefundPartyBankccount) {
		PotentialRefundPayment potentialRefundPayment = new PotentialRefundPayment();
		potentialRefundPayment.setPrmId(prmId);
		potentialRefundPayment.setSource(source);
		potentialRefundPayment.setDest(dest);
		potentialRefundPayment.setPaymentAmount(paymentAmt);
		potentialRefundPayment.setRPCode(rpCode);
		potentialRefundPayment.setPotentialRefundPartyBankccount(potentialRefundPartyBankccount);
		return potentialRefundPayment;
	}

}
