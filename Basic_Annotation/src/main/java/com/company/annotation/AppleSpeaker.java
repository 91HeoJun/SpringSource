package com.company.annotation;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("**** 애플 스피커 객체 생성");
	}
	
	@Override
	public void volUp() {
		System.out.println("애플 스피커 - 볼륨 업");
	}

	@Override
	public void volDown() {
		System.out.println("애플 스피커 - 볼륨 다운");
	}

}
