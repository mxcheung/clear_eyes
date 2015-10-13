package com.maxcheung.map;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.maxcheung.contribution.ContributionSchedule;
import com.maxcheung.contribution.DetailRecord;
import com.maxcheung.memberenrolment.MemberEnrolment; 

public class EmployerToMemberMapTest {

	private MemberEnrolment memberEnrolment = new MemberEnrolment();

	private ContributionSchedule contributionSchedule = new ContributionSchedule();

	@Before
	public void setUp() {
		memberEnrolment = new MemberEnrolment();
		List<com.maxcheung.memberenrolment.DetailRecord> x = memberEnrolment.getDetailRecord();

		contributionSchedule = new ContributionSchedule();
		List<com.maxcheung.contribution.DetailRecord> x1 = contributionSchedule.getDetailRecord();
	}

	@Test
	public void mapSingleMemDetailToCont() {

		com.maxcheung.memberenrolment.DetailRecord memDetail = createMemDetail("NIN123", "Smith");

		com.maxcheung.contribution.DetailRecord contDetail = memberToContribution.apply(memDetail);
		assertEquals("NIN123", contDetail.getNINumber());
		assertEquals("Smith", contDetail.getSurname());

	}

	@Test
	public void mapCollectionMemDetailToCont() {
		memberEnrolment = new MemberEnrolment();
		List<com.maxcheung.memberenrolment.DetailRecord> memDetails = memberEnrolment.getDetailRecord();

		memDetails.add(createMemDetail("NIN123", "Smith"));
		memDetails.add(createMemDetail("NIN124", "Smith"));
		memDetails.add(createMemDetail("NIN125", "Smith"));

		contributionSchedule = new ContributionSchedule();
		List<DetailRecord> contDetails = contributionSchedule.getDetailRecord();

		assertEquals(0, contDetails.size());

		List<com.maxcheung.contribution.DetailRecord> tempContDetail = memDetails.stream().map(memberToContribution)
				.collect(Collectors.<com.maxcheung.contribution.DetailRecord> toList());
		assertEquals(3, tempContDetail.size());

		tempContDetail.stream().forEach(item -> contDetails.add(item));
		assertEquals(3, contDetails.size());
		assertEquals(3, contributionSchedule.getDetailRecord().size());

	}

	@Test
	public void mapCollectionMemDetailToCont2() {
		memberEnrolment = new MemberEnrolment();
		List<com.maxcheung.memberenrolment.DetailRecord> memDetails = memberEnrolment.getDetailRecord();

		memDetails.add(createMemDetail("NIN123", "Smith"));
		memDetails.add(createMemDetail("NIN124", "Smith"));
		memDetails.add(createMemDetail("NIN125", "Smith"));

		contributionSchedule = new ContributionSchedule();
		List<DetailRecord> contDetails = contributionSchedule.getDetailRecord();

		assertEquals(0, contDetails.size());
		memDetails.stream().map(memberToContribution).forEach(item -> contDetails.add(item));
		assertEquals(3, contDetails.size());
		assertEquals(3, contributionSchedule.getDetailRecord().size());

	}

	private com.maxcheung.memberenrolment.DetailRecord createMemDetail(String niNumber, String surname) {
		com.maxcheung.memberenrolment.DetailRecord memDetail = new com.maxcheung.memberenrolment.DetailRecord();
		memDetail.setNINumber(niNumber);
		memDetail.setSurname(surname);
		return memDetail;
	}

	// Memberenrolment ==> Contribution
	Function<com.maxcheung.memberenrolment.DetailRecord, com.maxcheung.contribution.DetailRecord> memberToContribution = new Function<com.maxcheung.memberenrolment.DetailRecord, com.maxcheung.contribution.DetailRecord>() {
		public com.maxcheung.contribution.DetailRecord apply(com.maxcheung.memberenrolment.DetailRecord t) {
			com.maxcheung.contribution.DetailRecord contDetail = new com.maxcheung.contribution.DetailRecord();
			contDetail.setNINumber(t.getNINumber());
			contDetail.setSurname(t.getSurname());
			return contDetail;
		}
	};

}
