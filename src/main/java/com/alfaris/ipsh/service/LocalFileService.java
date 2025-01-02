package com.alfaris.ipsh.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alfaris.ipsh.entity.FileAdd;
import com.alfaris.ipsh.entity.LocalFile;
import com.alfaris.ipsh.exception.ResourceNotFoundException;
import com.alfaris.ipsh.repository.FileAddREpository;
import com.alfaris.ipsh.repository.LocalFileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class LocalFileService {

	private final LocalFileRepository localFileRepository;
	private final FileAddREpository addREpository;

	@Value("${file.upload-dir}")
	private String uploadDir;

	public void uploadFile(MultipartFile file) throws IOException {
		// Create directory if not exists
		Path dirPath = Paths.get(uploadDir);
		if (!Files.exists(dirPath)) {
			Files.createDirectories(dirPath);
		}

		// Save file to file system
		String fileName = file.getOriginalFilename();
		Path filePath = dirPath.resolve(fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		// Store to DB
		LocalFile fileEntity = new LocalFile();
		fileEntity.setFileName(fileName);
		fileEntity.setFilePath(filePath.toString());
		fileEntity.setSize(file.getSize());
		fileEntity.setFileData(file.getBytes());

		localFileRepository.save(fileEntity);
	}

	public String uplodeFileOnly(MultipartFile file) throws IOException {
		try {
		FileAdd fileAdd = FileAdd.builder().name(file.getOriginalFilename()).type(file.getContentType())
				.data(file.getBytes()).build();
		addREpository.save(fileAdd);
		return "File uploaded Successfully";
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return "Failed to upload the data";
		 
	}

	public FileAdd getFileById(long id) {
		return addREpository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Data Not found at this id"));
		
	}
}
