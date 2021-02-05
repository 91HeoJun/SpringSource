package com.company.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SimpleInterestCalculatorTest {

	private interestCalculator interestCal;
	
	@Before
	public void init() {
		interestCal = new SimpleInterestCalculator();
		interestCal.setRete(0.05);
	}
	
	@Test
	public void calculate() {	// 정상작동하는 경우
		double interest = interestCal.calculate(10000, 2);
		assertEquals(interest, 10000.0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illigalCalculate() {	// 위에 장성한 예외가 던져지길 기대하는 메소드
		interestCal.calculate(-10000, 2);
	}
	
}
