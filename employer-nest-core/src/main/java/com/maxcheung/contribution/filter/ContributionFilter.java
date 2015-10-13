package com.maxcheung.contribution.filter;

import java.util.ArrayList;
import java.util.List;

import com.maxcheung.employer.domain.Contribution;

/*
 * 
 *   1) Take the 1st employer contribution (if available)
 *   2) Take the 1st member level contribution ( if available) per member
 *   3)  Other remaining member contrib type contributions per member 
 *   4 ) Note 2) and 3) can mix and match across different members.
 */

public class ContributionFilter implements ContFilter {

	@Override
	public List<Contribution> filter(List<Contribution> contributions) {
		return applyFilters(contributions, createFilterChain());
	}
	
	private static List<Contribution> applyFilters(List<Contribution> contributions, List<ContFilter> filters) {
		List<Contribution> filteredContributions = contributions;
		for (ContFilter filter : filters) {
			filteredContributions = filter.filter(filteredContributions);
		}
		return filteredContributions;
	}

	private static List<ContFilter> createFilterChain() {
		List<ContFilter> filters = new ArrayList<ContFilter>();
		filters.add(new EmployerFilter());
		filters.add(new MemberFilter());
		return filters;
	}

}
