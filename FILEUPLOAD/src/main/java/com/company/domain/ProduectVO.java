package com.company.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProduectVO {
	
	private String code;
	private String category;
	private String pname;
	private int amount;
	private int price;
	private String etc;
//	private String file; 의미없음
}
