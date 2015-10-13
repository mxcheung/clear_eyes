package com.maxcheung.contribution.filter;

import static com.maxcheung.contribution.filter.ContributionFindFirst.findFirstItem;
import static com.maxcheung.contribution.filter.ContributionFindFirst.hasContribTypeContribution;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.maxcheung.employer.domain.Contribution;


/*
 * 
 *   1) Does the set contain employer level only ?
        Use 1st employer level

	Otherwise
    	- Split contribution by member
			- for each member
        		Does the member set contain memberlevel only ? 
        		   Yes ==>	Use 1st member (dedup at member level)
				   No  ==>  Use member contrib level  ( no deduping). 	  
 * 
 * 
 * 
 */
public class TestContributionFirstPredicates {

	private List<Contribution> contributions;

	@Before
	public void setUp() {
		Contribution c1 = new Contribution("Jones1", "Ref121", "EMPLOYER");
		Contribution c2 = new Contribution("Jones2", "Ref122", "MEMBERCONTRIBTYPE");
		Contribution c3 = new Contribution("Jones3", "Ref123", "MEMBER");
		Contribution c4 = new Contribution("Jones4", "Ref124", "EMPLOYER");
		Contribution c5 = new Contribution("Jones5", "Ref125", "MEMBER");
		Contribution c6 = new Contribution("Jones6", "Ref126", "EMPLOYER");
		Contribution c7 = new Contribution("Jones7", "Ref127", "MEMBERCONTRIBTYPE");
		Contribution c8 = new Contribution("Jones8", "Ref128", "MEMBER");
		Contribution c9 = new Contribution("Jones9", "Ref129", "EMPLOYER");
		Contribution c10 = new Contribution("Jones10", "Ref110", "EMPLOYER");
		contributions = new ArrayList<Contribution>();
		contributions.addAll(Arrays.asList(new Contribution[] { c1, c2, c3, c4, c5, c6, c7, c8, c9, c10 }));
	}

	@Test
	public void testFindFirstEmployerFilter() {
		Contribution firstContribution = findFirstItem(contributions, hasContribTypeContribution("EMPLOYER"));
		assertEquals("Jones1", firstContribution.getSurname());

	}
	
	@Test
	public void testFindFirstMemberFilter() {
		Contribution firstContribution = findFirstItem(contributions, hasContribTypeContribution("MEMBER"));
		assertEquals("Jones3", firstContribution.getSurname());
	}

	
	@Test
	public void testUnableToFind() {
		Contribution firstContribution = findFirstItem(contributions, hasContribTypeContribution("UNKNOWN"));
		assertNull(firstContribution);
	}
	
//	
//	@Test
//	public void testMemberContribTypeFilter() {
//		List<Contribution> filteredContributions = filterContributions(contributions,
//				isMemberContribTypeContribution());
//		assertEquals(10, contributions.size());
//		assertEquals(2, filteredContributions.size());
//	}
//
//	@Test
//	public void shouldUseEmployerContribution() {
//		Contribution c1 = new Contribution("Jones1","Ref121","MEMBER");
//		Contribution c2 = new Contribution("Jones2","Ref122","MEMBERCONTRIBTYPE");
//		Contribution c3 = new Contribution("Jones3","Ref123","MEMBER");
//		Contribution c4 = new Contribution("Jones4","Ref124","EMPLOYER");
//		Contribution c5 = new Contribution("Jones5","Ref125","MEMBER");
//	    contributions = new ArrayList<Contribution>();
//	    contributions.addAll(Arrays.asList(new Contribution[]{c1,c2,c3,c4,c5}));
//		List<Contribution>employerContributions = filterContributions(contributions, isEmployerContribution());
//		assertEquals(5, contributions.size());
//		Optional<Contribution> firstEmployer1 = employerContributions.stream().findFirst();
//		assertTrue(firstEmployer1.isPresent());
//		Contribution firstEmployer = firstEmployer1.get();
//		assertEquals("Jones4", firstEmployer.getSurname());
//	}
//
//	@Test
//	public void shouldNotFindEmployerContribution() {
//		Contribution c1 = new Contribution("Jones1","Ref121","MEMBER");
//		Contribution c2 = new Contribution("Jones2","Ref122","MEMBERCONTRIBTYPE");
//		Contribution c3 = new Contribution("Jones3","Ref123","MEMBER");
//		Contribution c4 = new Contribution("Jones4","Ref124","MEMBERCONTRIBTYPE");
//		Contribution c5 = new Contribution("Jones5","Ref125","MEMBER");
//	    contributions = new ArrayList<Contribution>();
//	    contributions.addAll(Arrays.asList(new Contribution[]{c1,c2,c3,c4,c5}));
//		List<Contribution>employerContributions = filterContributions(contributions, isEmployerContribution());
//		assertEquals(5, contributions.size());
//		Optional<Contribution> firstEmployer = employerContributions.stream().findFirst();
//		assertFalse(firstEmployer.isPresent());
//	}
//
//	
//	
//	@Test
//	public void shouldUseMemberContribution() {
//		Contribution c1 = new Contribution("Jones1","Ref121","MEMBER");
//		Contribution c2 = new Contribution("Jones2","Ref122","MEMBERCONTRIBTYPE");
//		Contribution c3 = new Contribution("Jones3","Ref123","MEMBER");
//		Contribution c4 = new Contribution("Jones4","Ref124","MEMBERCONTRIBTYPE");
//		Contribution c5 = new Contribution("Jones5","Ref125","MEMBER");
//	    contributions = new ArrayList<Contribution>();
//	    contributions.addAll(Arrays.asList(new Contribution[]{c1,c2,c3,c4,c5}));
//		List<Contribution>employerContributions = filterContributions(contributions, isEmployerContribution());
//		List<Contribution>memberContributions = filterContributions(contributions, isMemberContribution());
//		List<Contribution>memberContribType = filterContributions(contributions, isMemberContribTypeContribution());
//		assertEquals(5, contributions.size());
//		
//		if (employerContributions.size()> 0)
//		{
//			// use employer level ...
//		}
//		else
//		    // use member level	 
//			
//			// member override member contribtype///
//			
//		{
//			
//		}
//	}
//	
//	
//	// group by employer
////	Map<String, List<RefundEvent>> employerRefundEvents = refundEvents.stream()
////			.collect(Collectors.groupingBy(RefundEvent::getEmployer));
//	
//	
//	
//	
//	@Test
//	public void shouldGroupByMember() {
//		Contribution c1 = new Contribution("Jones1","Ref121","MEMBER");
//		Contribution c2 = new Contribution("Jones2","Ref122","MEMBERCONTRIBTYPE");
//		Contribution c3 = new Contribution("Jones1","Ref123","MEMBER");
//		Contribution c4 = new Contribution("Jones4","Ref124","MEMBERCONTRIBTYPE");
//		Contribution c5 = new Contribution("Jones2","Ref125","MEMBER");
//	    contributions = new ArrayList<Contribution>();
//	    contributions.addAll(Arrays.asList(new Contribution[]{c1,c2,c3,c4,c5}));
//	    
//	    Map<String, List<Contribution>> groupedMembers = contributions.stream()
//	    .collect(Collectors.groupingBy(Contribution::getSurname));
//
//		assertEquals(3, groupedMembers.size());
//
//		groupedMembers.forEach((k, v) -> {
//			System.out.print(k);   //  <==  dedupe each member
//		});
//
//	    
//	}
//	
	
	
	
	
}
