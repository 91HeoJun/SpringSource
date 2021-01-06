package com.company.student;

public class StudentInfo {

	private Student student;
	
	// 초기화 => 생성자 or Setter
	
	public StudentInfo(Student student) {
		this.student = student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}


	
	public void getStudentInfo() {
		if(student != null) {
			System.out.println("이름 : " +student.getName());
			System.out.println("나이 : " +student.getAge());
			System.out.println("학년 : " +student.getGradeName());
			System.out.print("학급 : " +student.getClassNum());

		
		}
	}



}