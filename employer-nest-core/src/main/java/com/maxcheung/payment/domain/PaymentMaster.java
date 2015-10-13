package com.maxcheung.payment.domain;

import java.util.List;

public class PaymentMaster {

	private List<ContributionPayment> contributionPayments;
	
	private int totalPayments ;

	private String otherInstructions ;
	
    private Boolean taxable; 


	public List<ContributionPayment> getContributionPayments() {
		return contributionPayments;
	}

	public void setContributionPayments(List<ContributionPayment> contributionPayments) {
		this.contributionPayments = contributionPayments;
	}

	public int getTotalPayments() {
		return totalPayments;
	}

	public void setTotalPayments(int totalPayments) {
		this.totalPayments = totalPayments;
	}

	public String getOtherInstructions() {
		return otherInstructions;
	}

	public void setOtherInstructions(String otherInstructions) {
		this.otherInstructions = otherInstructions;
	}

	public Boolean getTaxable() {
		return taxable;
	}

	public void setTaxable(Boolean taxable) {
		this.taxable = taxable;
	}


	
	
	
}
