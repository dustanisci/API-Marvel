package com.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvel.model.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
	
}
