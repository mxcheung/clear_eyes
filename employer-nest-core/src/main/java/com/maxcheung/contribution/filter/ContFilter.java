package com.maxcheung.contribution.filter;

import java.util.List;

import com.maxcheung.employer.domain.Contribution;


public interface ContFilter {

	  public List<Contribution> filter(List<Contribution> contributions);
}
