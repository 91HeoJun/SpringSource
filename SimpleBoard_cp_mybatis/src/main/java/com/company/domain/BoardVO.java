package com.company.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private Date updatedate;
	
}