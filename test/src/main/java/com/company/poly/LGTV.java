package com.company.poly;

public class LGTV implements TV {
	// 멤버변수로 선언
	private Speaker speaker;
	
	public LGTV() {
		System.out.println("LGTV 객체 생성");
	}
		
//	public LGTV(Speaker speaker) {
//		this.speaker = speaker;
//	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void trunOn() {
		System.out.println("LGTV - 전원 On");
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
