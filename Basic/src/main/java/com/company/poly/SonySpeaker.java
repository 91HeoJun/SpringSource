package com.company.poly;

public class SonySpeaker implements Speaker {
 public SonySpeaker() {
	System.out.println("==== Sony Speaker 객체 생성"); 
 }
 
 @Override
public void volUp() {
	 System.out.println("==== Sony Speaker 볼륨 Up");
 }

 @Override
public void volDown() {
	 System.out.println("==== Sony Speaker 볼륨 Down");
 }
}
