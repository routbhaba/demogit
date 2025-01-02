package com.alfaris.ipsh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alfaris.ipsh.entity.FileAdd;
import com.alfaris.ipsh.service.LocalFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class Localfilecontroller {

	private final LocalFileService localFileService;

	@PostMapping("/addfile")
	// upload file to database with store in the file system
	public ResponseEntity<String> uploadFileToDatabase(@RequestPart MultipartFile file) {
		try {
			localFileService.uploadFile(file);
			return new ResponseEntity<>("FIle uploaded successfully", new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Failed to uploaded the file", new HttpHeaders(), HttpStatus.OK);
		}
	}

	@PostMapping("/add")
	// upload file to database
	public ResponseEntity<String> postMethodName(@RequestPart MultipartFile file) throws IOException {

		return new ResponseEntity<>(localFileService.uplodeFileOnly(file), new HttpHeaders(), HttpStatus.OK);

	}
	
	@GetMapping("/get")
	public ResponseEntity<byte[]> getMethodName(@RequestParam("id") long id) {
		FileAdd file=localFileService.getFileById(id);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .body(file.getData());

	}

	
	
 
}
