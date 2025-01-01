package com.alfaris.ipsh.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alfaris.ipsh.entity.LocalFile;
import com.alfaris.ipsh.repository.LocalFileRepository;

@Service
public class LocalFileService {
	
	@Autowired
	private LocalFileRepository localFileRepository;

	public void uploadFile(MultipartFile file) throws IOException {
		        LocalFile entity = new LocalFile();
		        entity.setName(file.getOriginalFilename());
		        entity.setType(file.getContentType());
		        entity.setData(file.getBytes());
		        localFileRepository.save(entity);
		    }
	}


