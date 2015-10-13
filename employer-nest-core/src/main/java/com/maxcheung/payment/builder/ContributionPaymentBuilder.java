package com.maxcheung.payment.builder;

import com.maxcheung.employer.domain.Contribution;
import com.maxcheung.payment.domain.ContributionPayment;

public class ContributionPaymentBuilder {

	private Contribution contribution;

	public ContributionPaymentBuilder() {
	}

	public ContributionPaymentBuilder copyFrom(Contribution contribution) {
		this.contribution = contribution;
		return this;
	}

	public ContributionPayment build() {
		ContributionPayment ret = new ContributionPayment();
		ret.setEmployerReference(contribution.getEmployerReference());
		ret.setSurname(contribution.getSurname());
		return ret;
	}

}
