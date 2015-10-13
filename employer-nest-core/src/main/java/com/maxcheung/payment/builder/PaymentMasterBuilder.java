package com.maxcheung.payment.builder;

import java.util.List;

import com.maxcheung.payment.domain.ContributionPayment;
import com.maxcheung.payment.domain.PaymentMaster;

public class PaymentMasterBuilder {

	private List<ContributionPayment> contributionPayments;
	private String otherInstructions;
	private Boolean taxable;

	public PaymentMasterBuilder() {
	}

	public PaymentMasterBuilder setOtherInstructions(String otherInstructions) {
		this.otherInstructions = otherInstructions;
		return this;
	}

	public PaymentMasterBuilder setTaxable(Boolean taxable) {
		this.taxable = taxable;
		return this;
	}

	public PaymentMasterBuilder setContributionPayments(List<ContributionPayment> contributionPayments) {
		this.contributionPayments = contributionPayments;
		return this;
	}

	public PaymentMasterBuilder copyContributionPaymentsFrom(List<ContributionPayment> contributionPayments) {
		return this.setContributionPayments(contributionPayments);
	}

	public PaymentMaster build() {
		PaymentMaster paymentMaster = new PaymentMaster();
		paymentMaster.setContributionPayments(contributionPayments);
		paymentMaster.setOtherInstructions(otherInstructions);
		paymentMaster.setTaxable(this.taxable == null ? false : this.taxable);
		return paymentMaster;
	}

}
