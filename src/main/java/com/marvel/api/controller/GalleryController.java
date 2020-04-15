package com.marvel.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Gallery> findAll(@PathVariable(required = true) Long id) {
		return galleryRepository.findBySuperhero_Id(id);
	}

	@Transactional
	@PostMapping()
	public ResponseEntity<?> insert(@ModelAttribute GalleryDto galleryDto) {
		ListError listErrorImages = new ListError();

		for (MultipartFile image : galleryDto.getImages()) {

			try {
				String nameArchive = System.currentTimeMillis() + galleryDto.getExtension(image);
				String directory = new File("").getAbsolutePath().replace("\\", "/") + "/images/" + nameArchive;

				FileOutputStream fileOutputStream = new FileOutputStream(directory);
				fileOutputStream.write(image.getBytes());
				fileOutputStream.close();

				galleryDto.setUrl("http://localhost:8080/images/" + nameArchive);
				galleryDto.setPhotoName(nameArchive);
				galleryRepository.save(galleryDto.parseToGallery());

			} catch (Exception e) {
				listErrorImages.setErrorList(image.getOriginalFilename());
			}
		}

		return ResponseEntity.ok(listErrorImages);
	}

	@Transactional
	@DeleteMapping("/{ids}")
	public ResponseEntity<?> deleteIds(@PathVariable(required = true) List<Long> ids) {
		for (long id : ids) {
			Optional<Gallery> optional = galleryRepository.findById(id);

			if (optional.isPresent()) {

				String directory = new File("").getAbsolutePath().replace("\\", "/") + "/images/"
						+ galleryRepository.getOne(id).getName();

				System.out.println(directory);
				new File(directory).delete();
				galleryRepository.deleteById(id);
			}
		}
		return ResponseEntity.ok().build();
	}

}
