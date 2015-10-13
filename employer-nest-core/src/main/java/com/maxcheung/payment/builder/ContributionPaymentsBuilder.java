package com.maxcheung.payment.builder;

import java.util.ArrayList;
import java.util.List;

import com.maxcheung.employer.domain.Contribution;
import com.maxcheung.payment.domain.ContributionPayment;

public class ContributionPaymentsBuilder {

	private List<Contribution> contributions;
	
	public ContributionPaymentsBuilder() {
	}

	public ContributionPaymentsBuilder copyFrom(List<Contribution> contributions) {
		this.contributions = contributions;
		return this;
	}

	public List<ContributionPayment> build() {

		List<ContributionPayment> contributionPayments = new ArrayList<ContributionPayment>();
		for (Contribution contribution : contributions) {
			ContributionPayment contributionPayment = new ContributionPayment();
			contributionPayment.setSurname(contribution.getSurname());
			contributionPayments.add(contributionPayment);
		}
		return contributionPayments;
	}

}
