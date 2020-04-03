package com.marvel.api.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marvel.api.dto.GalleryDto;
import com.marvel.api.model.ListError;
import com.marvel.api.repository.GalleryRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping({ "/gallery", "/gallery/" })
public class GalleryController {

	@Autowired
	private GalleryRepository galleryRepository;

	@Transactional
	@PostMapping
	public ResponseEntity<?> insert(@ModelAttribute GalleryDto galleryDto) {
		ListError listErrorImages = new ListError();

		for (MultipartFile image : galleryDto.getImages()) {
			try {
				String directory = new File("").getAbsolutePath() + "\\images\\" + System.currentTimeMillis()
						+ this.getExtension(image);

				FileOutputStream fileOutputStream = new FileOutputStream(directory);
				fileOutputStream.write(image.getBytes());
				fileOutputStream.close();

				galleryDto.setUrl(directory);
				galleryRepository.save(galleryDto.parseToGallery());

			} catch (Exception e) {
				listErrorImages.setErrorList(image.getOriginalFilename());
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(listErrorImages);
	}

	public String getExtension(MultipartFile image) throws ObjectNotFoundException {
		if (image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/jpg")) {
			return ".jpg";
		} else if (image.getContentType().equals("image/png")) {
			return ".png";
		}
		throw new RuntimeException();
	}

}
