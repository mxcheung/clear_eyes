package com.maxcheung.contribution.filter;

import static org.junit.Assert.assertEquals;

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
public class TestContributionFilter {

	private List<Contribution> contributions;
	
	private ContributionFilter contributionFilter =  new ContributionFilter();

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
	public void testEmployerFilter() {
		assertEquals(10, contributions.size());
		List<Contribution> filteredContributions = contributionFilter.filter(contributions);
		assertEquals(1, filteredContributions.size());
		assertEquals(10, contributions.size());
	}
	
	@Test
	public void testMemberFilter() {
		Contribution c1 = new Contribution("Jones2", "Ref122", "MEMBERCONTRIBTYPE");
		Contribution c2 = new Contribution("Jones3", "Ref123", "MEMBER");
		Contribution c3 = new Contribution("Jones5", "Ref125", "MEMBER");
		Contribution c4 = new Contribution("Jones3", "Ref127", "MEMBERCONTRIBTYPE");
		Contribution c5 = new Contribution("Jones8", "Ref128", "MEMBER");
		contributions = new ArrayList<Contribution>();
		contributions.addAll(Arrays.asList(new Contribution[] { c1, c2, c3, c4, c5 }));
		assertEquals(5, contributions.size());
		List<Contribution> filteredContributions = contributionFilter.filter(contributions);
		assertEquals(4, filteredContributions.size());
		assertEquals(5, contributions.size());
	}


	@Test
	public void testMember2Filter() {
		Contribution c1 = new Contribution("Jones2", "Ref122", "MEMBERCONTRIBTYPE");
		Contribution c2 = new Contribution("Jones2", "Ref123", "MEMBERCONTRIBTYPE");
		Contribution c3 = new Contribution("Jones5", "Ref125", "MEMBER");
		Contribution c4 = new Contribution("Jones3", "Ref127", "MEMBERCONTRIBTYPE");
		Contribution c5 = new Contribution("Jones8", "Ref128", "MEMBER");
		contributions = new ArrayList<Contribution>();
		contributions.addAll(Arrays.asList(new Contribution[] { c1, c2, c3, c4, c5 }));
		assertEquals(5, contributions.size());
		List<Contribution> filteredContributions = contributionFilter.filter(contributions);
		assertEquals(5, filteredContributions.size());
		assertEquals(5, contributions.size());
	}

	
	
	@Test
	public void testMember3Filter() {
		Contribution c1 = new Contribution("Jones2", "Ref122", "MEMBERCONTRIBTYPE");
		Contribution c2 = new Contribution("Jones2", "Ref123", "MEMBERCONTRIBTYPE");
		Contribution c3 = new Contribution("Jones2", "Ref125", "MEMBER");
		Contribution c4 = new Contribution("Jones3", "Ref127", "MEMBERCONTRIBTYPE");
		Contribution c5 = new Contribution("Jones8", "Ref128", "MEMBER");
		contributions = new ArrayList<Contribution>();
		contributions.addAll(Arrays.asList(new Contribution[] { c1, c2, c3, c4, c5 }));
		assertEquals(5, contributions.size());
		List<Contribution> filteredContributions = contributionFilter.filter(contributions);
		assertEquals(3, filteredContributions.size());
		assertEquals(5, contributions.size());
	}

	

	
	
	
	
}
