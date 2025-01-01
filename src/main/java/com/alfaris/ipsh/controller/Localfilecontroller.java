package com.alfaris.ipsh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alfaris.ipsh.service.LocalFileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class Localfilecontroller {
	
	private  LocalFileService localFileService;
	
	@GetMapping("/addfile")
	public ResponseEntity<String> getMethodName(@RequestParam("file") MultipartFile file) {
		   try {
			   localFileService.uploadFile(file);
	            return new ResponseEntity<>("FIle uploaded successfully",new HttpHeaders(),HttpStatus.OK);
	        } catch (Exception e) {
	            return new  ResponseEntity<>("Failed to upload the file",new HttpHeaders(),HttpStatus.OK);
	        }
	    }
	}
	


