package com.company.app;

public class TVUser {
	public static void main(String[] args) {
		LGTV lg = new LGTV();
	
		lg.trunOn();
		lg.soundUp();
		lg.soundDown();
		lg.trunOff();
		
		SamsungTV sst = new SamsungTV();
		sst.powerOn();
		sst.volumUp();
		sst.volumDown();
		sst.powerOff();
		
	}
}
