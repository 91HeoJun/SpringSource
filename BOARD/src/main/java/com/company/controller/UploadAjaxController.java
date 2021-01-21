package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.FileAttach;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Controller
public class UploadAjaxController {

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
		String uploadFolderPath = getFolder();	// 2021\\01\\20
		File uploadPath = new File(uploadFolder, uploadFolderPath);	// c:\\upload\\2021\\01\\20
		
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		// 첨부 파일에 대한 정보를 담을 객체 생성
		List<FileAttach> attachList = new ArrayList<>();
		
		for (MultipartFile f: uploadFile) {
			log.info("---------------------");
			log.info("upload file name : " + f.getOriginalFilename());
			log.info("upload file size : " + f.getSize());
			
			// 파일명 중복 해결
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + f.getOriginalFilename();
			
			FileAttach attach = new FileAttach();
			attach.setFileName(f.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());

			File saveFile = new File(uploadPath, uploadFileName);

			try {

				// 이미지인지 아닌지 여부 확인
				if (checkImageType(saveFile)) {
					attach.setFileType(true);
					
					// 이미지 일 경우 썸네일로 한번 더 저장
					// c:\\upload\\2021\\01\\20, s_uuid_원본파일명
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					InputStream in = f.getInputStream();
					Thumbnailator.createThumbnail(in, thumbnail, 100, 100);
					in.close();
					thumbnail.close();
				}

				// ** 서버에 저장 **
				f.transferTo(saveFile);
				attachList.add(attach);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<FileAttach>>(attachList, HttpStatus.OK);
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("---- 썸네일 요청 ... ---- " + fileName);
		File f = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("content-Type", Files.probeContentType(f.toPath()));
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(f), headers,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return entity;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();	// 시간, 날짜가 길게 나옴
		String str = sdf.format(date);	// 2021-01-20
		return str.replace("-", File.separator);	// 2021\01\20
		// 폴더 구분시 사용하는 문자 - windows: \, 리눅스: / 로 각각 다르므로  File.separator로 자동 조정 하는 법을 권장함.
	}
	
	// -----------------------------------------------------------------------------------
	// -------------------------------------- 다운로드 --------------------------------------
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		log.info("다운로드 요청" + fileName);

		Resource resource = new FileSystemResource("C:\\upload\\" + fileName);
		
		// uuid+fileName
		String resourceUidName = resource.getFilename();
		// uuid+fileName.subestring(uuid+fileName의 index로("_")를 지정하고 이후 +1개부터 컷
		String resourceName = resourceUidName.substring(resourceUidName.indexOf("_")+1);
		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Disposition", "attachment;filename=" + new String(resourceName.getBytes("utf-8"), "ISO-8859-1"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	// --------------------------------------------------------------------------------
	// -------------------------------------- 삭제 --------------------------------------
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("파일삭제 : " +fileName+ " 타입 : " +type);
		try {
			File file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
			
			// 파일(썸네일, 일반) 삭제
			file.delete();
			
			if (type.equals("image")) { // 이미지일 경우 이미지 원본 삭제
				String oriName = file.getAbsolutePath().replace("s_", "");
				file = new File(oriName);
				file.delete();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("file", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}