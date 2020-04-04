package com.marvel.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marvel.api.dto.GalleryDto;
import com.marvel.api.model.Gallery;
import com.marvel.api.model.ListError;
import com.marvel.api.repository.GalleryRepository;

@RestController
@CrossOrigin
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryRepository galleryRepository;

	@GetMapping("/{id}")
	public List<Gallery> findAll(@PathVariable Long id) {
		return galleryRepository.findBySuperhero_Id(id);	
	}

	@Transactional
	@PostMapping
	public ResponseEntity<?> insert(@ModelAttribute GalleryDto galleryDto) {
		ListError listErrorImages = new ListError();

		for (MultipartFile image : galleryDto.getImages()) {
			try {
				String directory = (new File("").getAbsolutePath() + "\\images\\" + System.currentTimeMillis()
						+ galleryDto.getExtension(image)).replace("\\", "/");

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

	@Transactional
	@DeleteMapping
	public ResponseEntity<?> deleteIds(@RequestBody List<Long> ids) {
		for (long id : ids) {
			Optional<Gallery> optional = galleryRepository.findById(id);
			
			if (optional.isPresent()) {	
				new File(galleryRepository.getOne(id).getUrl()).delete();
				galleryRepository.deleteById(id);
			}
		}
		return ResponseEntity.ok().build();
	}

}
