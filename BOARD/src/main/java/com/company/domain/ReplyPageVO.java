package com.company.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReplyPageVO {

	private int replyCnt; // 게시물 댓글 수
	private List<ReplyVO> list;
		
}