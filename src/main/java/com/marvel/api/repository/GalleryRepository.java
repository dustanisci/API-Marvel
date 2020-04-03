package com.marvel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvel.api.model.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}