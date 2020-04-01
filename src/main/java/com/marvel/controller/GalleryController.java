package com.marvel.controller;

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.model.Gallery;
import com.marvel.repository.GalleryRepository;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryRepository galleryRepository;

	@Transactional
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody String imgValue, HttpServletRequest request){
		
		try {
            byte[] imageByte = Base64.decodeBase64(imgValue);
            
            String directory = request
            					.getSession()
            					.getServletContext()
            					.getRealPath("/")+"images/sample.jpg";

            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            fileOutputStream.write(imageByte);
            fileOutputStream.close();
            
            Gallery gallery = new Gallery();
            gallery.setUrl(directory);
            
            galleryRepository.save(gallery);
            
            return ResponseEntity.ok().build();
        }
        catch(Exception e) {
        	return  (ResponseEntity<?>) ResponseEntity.badRequest();
        }
		
	}
}
