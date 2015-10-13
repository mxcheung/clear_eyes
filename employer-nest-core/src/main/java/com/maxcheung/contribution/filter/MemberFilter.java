package com.maxcheung.contribution.filter;

import static com.maxcheung.contribution.filter.ContributionFindFirst.hasContribTypeContribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.maxcheung.employer.domain.Contribution;


public class MemberFilter implements ContFilter {

	private static final String MEMBER = "MEMBER";

	@Override
	public List<Contribution> filter(List<Contribution> contributions) {
		return contributionsByMember(contributions);
	}
	
	

	private static List<Contribution> memberLevelFilter( List<Contribution> contributions) {
		Contribution memberContribution = ContributionFindFirst.findFirstItem(contributions,
				hasContribTypeContribution(MEMBER));
		return (memberContribution != null) ? Arrays.asList(memberContribution) : contributions;
	}
	
	private static List<Contribution> contributionsByMember(List<Contribution> contributions) {
		List<Contribution> memberContributions = new ArrayList<Contribution>();
		Map<String, List<Contribution>> groupedMembers = contributions.stream()
				.collect(Collectors.groupingBy(Contribution::getSurname));
		groupedMembers.forEach((k, v) -> {
			memberContributions.addAll(memberLevelFilter(v));
		});
		return memberContributions;
	}


}
