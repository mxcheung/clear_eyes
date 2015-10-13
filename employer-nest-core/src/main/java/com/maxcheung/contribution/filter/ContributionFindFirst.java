package com.maxcheung.contribution.filter;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import com.maxcheung.employer.domain.Contribution;

/*
 * 
 *   1) Find 1st employer contribution
 *   2) Split by member ==> find 1st member
 *   3  Use remaining member list
 * 
 */

public class ContributionFindFirst {

	 public static Predicate<Contribution> hasContribTypeContribution(String contribType) {
	        return p -> p.getContribType().equalsIgnoreCase(contribType); 
	    }

	 public static Contribution findFirstItem (List<Contribution> contributions, Predicate<Contribution> predicate) {
		 Optional<Contribution> firstItem = contributions.stream().filter( predicate ).findFirst();
		 return firstItem.isPresent() ? firstItem.get() : null;
	 }

}


