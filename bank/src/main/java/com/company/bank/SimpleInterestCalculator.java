package com.company.bank;

public class SimpleInterestCalculator implements interestCalculator {

	private double rate;
	
	@Override
	public void setRete(double rate) {
		this.rate = rate;

	}

	@Override
	public double calculate(double amount, double year) {

		if (amount <0 || year < 0) {
			throw new IllegalArgumentException("Amount or year must be Positive");
		}
		
		return amount * year * rate;
	}

}
