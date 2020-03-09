package com.transport.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.transport.configuration.FileStorageProperties;
import com.transport.exception.FileStorageException;
import com.transport.service.FileStorageService;
@Service
public class FileStorageServiceImpl implements FileStorageService {
	@Value("${file.upload.dir}")
	private String directory;
	
	
//	@Autowired
//	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
//		this.fileStorageLocation = Paths.get(directory).toAbsolutePath().normalize();
//		try {
//			Files.createDirectories(this.fileStorageLocation);
//		} catch (Exception ex) {
//			throw new FileStorageException("CR0071", ex);// Could not create the
//															// directory where
//															// the uploaded
//															// files will be
//															// stored.
//		}
//	}
	@Override
	public String storeFile(MultipartFile file) {
		Path fileStorageLocation = Paths.get(directory).toAbsolutePath().normalize();
		System.out.println("lcoation"+fileStorageLocation);
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("file name : "+fileName);
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("CR0072");// Sorry! Filename
															// contains invalid
															// path sequence
			}
			// Copy file to the target location (Replacing existing file with
			// the same name)
			Path targetLocation = fileStorageLocation.resolve(fileName);
			System.out.println("traget location"+targetLocation);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("CR0073", ex);// Could not store
															// file. Please try
															// again!
		}
	}
}
