package com.marvel.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Gallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gallery_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "superhero_id")
	public SuperHero superhero;

	private String url;
	
	public Gallery(String url, SuperHero superhero) {
		this.setUrl(url);
		this.setSuperhero(superhero);
	}

	public long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SuperHero getSuperhero() {
		return superhero;
	}

	public void setSuperhero(SuperHero superhero) {
		this.superhero = superhero;
	}
	
}