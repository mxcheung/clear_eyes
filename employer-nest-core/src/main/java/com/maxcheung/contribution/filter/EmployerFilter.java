package com.maxcheung.contribution.filter;

import static com.maxcheung.contribution.filter.ContributionFindFirst.hasContribTypeContribution;

import java.util.Arrays;
import java.util.List;

import com.maxcheung.employer.domain.Contribution;


public class EmployerFilter implements ContFilter {

	private static final String EMPLOYER = "EMPLOYER";

	@Override
	public List<Contribution> filter(List<Contribution> contributions) {
		Contribution employerContribution = ContributionFindFirst.findFirstItem(contributions,
				hasContribTypeContribution(EMPLOYER));
		return (employerContribution != null) ?  Arrays.asList(employerContribution) : contributions;

	}

}
