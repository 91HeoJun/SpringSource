package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;
import com.company.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies/*")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@PostMapping(value="/new")
	public ResponseEntity<String> create(@RequestBody ReplyVO reVO) {
		log.info("---- 댓글 삽입중 ... ----" + reVO);
	
		return service.addReply(reVO)? new ResponseEntity<String>("Add Success", HttpStatus.OK):
			new ResponseEntity<String>("Add Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value="/{rno}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> select(@PathVariable("rno") int rno){
		log.info("---- 댓글 하나 가져오는중 ... ----" + rno);

		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	@GetMapping(value="/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyPageVO> getList(@PathVariable("bno")int bno, @PathVariable("page")int page) {
		log.info("---- 전체 조회 리스트 가져오는중 ... ----" + bno, page);
		Criteria cri = new Criteria(page, 10);

		return new ResponseEntity<ReplyPageVO>(service.getList(cri, bno), HttpStatus.OK);
	}

	@PutMapping("/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO reVO){
		log.info("---- 댓글 수정중 ... ----");
		
		reVO.setRno(rno);
		
		return service.updateReply(reVO)? new ResponseEntity<String>("Update Success", HttpStatus.OK):
			new ResponseEntity<String>("Update Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		log.info("---- 댓글 삭제중 ... ----");
		return service.deleteReply(rno)? new ResponseEntity<String>("Delete Success", HttpStatus.OK):
			new ResponseEntity<String>("Delete Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
