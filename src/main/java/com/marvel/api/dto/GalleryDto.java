package com.marvel.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marvel.api.model.Gallery;
import com.marvel.api.model.SuperHero;

public class GalleryDto {

	private long superHeroId;
	private List<MultipartFile> images = new ArrayList<MultipartFile>();
	private String url;

	public long getSuperHeroId() {
		return superHeroId;
	}

	public void setSuperHeroId(long superHeroId) {
		this.superHeroId = superHeroId;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Gallery parseToGallery() {
		SuperHero superHero = new SuperHero();
		superHero.setId(superHeroId);
		return new Gallery(url, superHero);
	}

}