package com.company.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVO {	// 페이지 나누기에 관한 모든 정보를 담은 객체
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int total;
	private Criteria cri;	// 페이지번호 & 페이지 당 개수
	
	public PageVO(Criteria cri, int total) {
		this.total = total;	// 전체 게시물 수
		this.cri = cri;		// 페이지번호 & 페이지 당 개수
		
		// page  3 요청 시 => 1~10
		// page 12 요청 시 => 10~20
		this.endPage = (int) (Math.ceil(cri.getPageNum()/10.0) * 10);
		this.startPage = endPage - 9;
		
		// page 3에서 게시물 수가 40개 일 경우 => 1~4
		int realEnd = (int) (Math.ceil((total/1.0) / cri.getAmount()));
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		// 다음&이전 페이지로
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	

}
