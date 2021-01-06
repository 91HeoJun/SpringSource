package com.company.poly;

public class LGTV implements TV {
	// 멤버변수로 선언
	private Speaker speaker;
	private int price;
	
	
//	public LGTV() {
//		speaker = new SonySpeaker();
//	}
	
//	public LGTV(Speaker speaker) {
//		this.speaker = speaker;
//	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	
	@Override
	public void trunOn() {
		System.out.println("LGTV - 전원 On, 가격 : "+price);
	}
	@Override
	public void trunOff() {
		System.out.println("LGTV - 전원 Off");
	}
	@Override
	public void soundUp() {
		//System.out.println("LGTV - 볼륨 up");
		speaker.volUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LGTV - 볼륨 Down");
		speaker.volDown();
	}
}
