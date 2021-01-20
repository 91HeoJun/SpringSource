package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.domain.ProduectVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {
	
//	@GetMapping("/product")
//	public void productForm() {
//		log.info("---- productForm 호출중... ----");
//	}
	
	/*
	 * @ResponseBody
	 * @PostMapping("/product") public String getProduct(ProduectVO Pvo){
	 * log.info("---- product 가져오는중 .... ----" + Pvo);
	 * 
	 * return "getProduct Success";
	 * 
	 * }
	 */

	
//	  @PostMapping("/product")
//	  public ResponseEntity<String> productPost(@RequestBody ProduectVO Pvo) {
//		 log.info("---- 데이터 가져오기... ---- " + Pvo );
//	  
//		 return Pvo!=null?
//				 new ResponseEntity<String>("success", HttpStatus.OK):
//				 new ResponseEntity<String>("fale", HttpStatus.BAD_REQUEST);
//	  
//	  }
	 
	
	/*
	 * @ResponseBody
	 * @PostMapping(value="/product", produces =
	 * MediaType.APPLICATION_JSON_UTF8_VALUE) public ProduectVO
	 * getProduct(ProduectVO Pvo){ log.info("---- product 가져오는중 .... ----" + Pvo);
	 * 
	 * return Pvo; 
	 * }
	 */

//	  @PostMapping(value="/product", produces =	MediaType.APPLICATION_JSON_UTF8_VALUE)
//		public ResponseEntity<ProduectVO> getProduct(ProduectVO Pvo) {
//			 log.info("---- product 가져오는중 .... ----" + Pvo);
//	 
//	  	return new ResponseEntity<ProduectVO>(Pvo, HttpStatus.OK); 
//	  }
	 
//	 @ResponseBody 
//	 @PostMapping(value="/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	 public List<ProduectVO> getProduct(ProduectVO Pvo) {
//		 log.info("---- product 가져오는중 .... ----" + Pvo);
//	
//		 List<ProduectVO> list = new ArrayList<ProduectVO>();
//	 
//		 for (int i=0; i<=3; i++) { list.add(Pvo); }
//
//	 return list;
//	 }
	
//	  	@ResponseBody 
//		@PostMapping(value="/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	  	public ResponseEntity<List<ProduectVO>> getProduct(ProduectVO Pvo){
//	  		log.info("---- product 가져오는중 .... ----" + Pvo);
//
//	  		List<ProduectVO> list = new ArrayList<ProduectVO>();
//		
//	  		for (int i=0; i<=3; i++) {
//	  			list.add(Pvo);
//	  		}
//		
//	  		return new ResponseEntity<List<ProduectVO>>(list, HttpStatus.OK);
//		}
	  
}