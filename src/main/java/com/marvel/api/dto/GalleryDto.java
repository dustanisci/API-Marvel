package com.marvel.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marvel.api.model.Gallery;
import com.marvel.api.model.SuperHero;

import javassist.tools.rmi.ObjectNotFoundException;

public class GalleryDto {

	private long superHeroId;
	private List<MultipartFile> images = new ArrayList<MultipartFile>();
	private String url;
	private String name;

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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gallery parseToGallery() {
		return new Gallery(url, new SuperHero(superHeroId));
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