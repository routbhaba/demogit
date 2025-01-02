package com.alfaris.ipsh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alfaris.ipsh.service.LocalFileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class Localfilecontroller {
	
	private final LocalFileService localFileService;
	
	@PostMapping("/addfile")
	public ResponseEntity<String> getMethodName(@RequestPart MultipartFile file) {
		   try {
			   localFileService.uploadFile(file);
	            return new ResponseEntity<>("FIle uploaded successfully",new HttpHeaders(),HttpStatus.OK);
	        } catch (Exception e) {
	        	System.out.println(e);
	            return new  ResponseEntity<>("Failed to uploaded the file",new HttpHeaders(),HttpStatus.OK);
	        }
	    }
	}
	


