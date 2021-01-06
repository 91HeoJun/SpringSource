package com.company.annotation;

public class SamsungTV implements TV {

	@Override
	public void trunOn() {
		System.out.println("삼성TV - 전원 On");

	}

	@Override
	public void trunOff() {
		System.out.println("삼성TV - 전원 Off");

	}

	@Override
	public void soundUp() {
		System.out.println("삼성TV - 볼륨 Up");

	}

	@Override
	public void soundDown() {
		System.out.println("삼성TV - 볼륨 Down");

	}

}
