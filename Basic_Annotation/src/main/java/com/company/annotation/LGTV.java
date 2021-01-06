package com.company.annotation;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv") // 객체생성 자동 <bean id="tv" class="com.company.annotation.LGTV"/>를 대체
public class LGTV implements TV {
	
	// 적절한 시점에 생성된 객체 주입(@Autowired, @Inject 동일)
	// 이름 구별을 위한 사용(@Qualifier)
	// 객체주입 + 이름구별 = @resour
	
	@Inject
	@Qualifier("apple") // 이름 구별을 위한 사용 단독사용 불가(주입 대상이 여러개인 경우)
	private Speaker speaker;
	
//	private int price;
	
//	public void setSpeaker(Speaker speaker) {
//	this.speaker = speaker;
//	}

//	public void setPrice(int price) {
//	this.price = price;
//	}
	
//	public LGTV() {
//		speaker = new SonySpeaker();
//	}
	
//	public LGTV(Speaker speaker) {
//		this.speaker = speaker;
//	}

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