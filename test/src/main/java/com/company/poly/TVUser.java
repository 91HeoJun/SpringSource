package com.company.poly;

public class TVUser {
	public static void main(String[] args) {
		// 다형성
		//TV lg = new SamsungTV();
		TV lg = new LGTV();
		//lg.setSpeaker(new AppleSpeaker)
		lg.trunOn();
		lg.soundUp();
		lg.soundDown();
		lg.trunOff();
	}
}