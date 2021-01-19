package com.company.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.FileAttach;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class uploadAjaxController {

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("ajax 업로드 폼 요청");
	}

	@PostMapping(value="/uploadAjax", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> uploadPost(MultipartFile[] uploadFile) {
		log.info("---- upload 요청중 ... ----");
		
		String uploadFolder = "C:\\upload";
		String uploadFileName = null;
		
		// 폴더 생성
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		// 첨부 파일에 대한 정보를 담을 객체 생성
		List<FileAttach> attachList = new ArrayList<>();
		
		for (MultipartFile f: uploadFile) {
			log.info("---------------------");
			log.info("upload fiel name : " + f.getOriginalFilename());
			log.info("upload fiel size : " + f.getSize());
			
			// 파일명 중복 해결
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + f.getOriginalFilename();
			
			FileAttach attach = new FileAttach();
			attach.setFileName(f.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());

			File saveFile = new File(uploadPath, uploadFileName);

			try {
				// ** 서버에 저장 **
				f.transferTo(saveFile);
				// 이미지인지 아닌지 여부 확인
				if (checkImageType(saveFile)) {
					attach.setImage(true);
				}
				attachList.add(attach);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<FileAttach>>(attachList, HttpStatus.OK);
	}
	
	// 서버에 저장한 파일이 이미지인지 아닌지 여부 확인
	private boolean checkImageType(File file) {	// ~.txt => text/plain, text/html, image/jpeg .....

//			String contentType =Files.probeContentType(file.toPath());
//			return contentType.startsWith("image");

			MimetypesFileTypeMap m = new MimetypesFileTypeMap();
			m.addMimeTypes("image png jpg jpeg gif"); // MimeType 추가 희망 시 나열해서 넣을 수 있음.

			return m.getContentType(file).contains("image");
	}
	
	// 날짜에 따라 폴더 생성하기
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
		// 폴더 구분시 사용하는 문자 - windows: \, 리눅스: / 로 각각 다르므로  File.separator로 자동 조정 하는 법을 권장함.
	}
}