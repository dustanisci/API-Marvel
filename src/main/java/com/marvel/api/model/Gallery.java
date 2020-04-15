package com.marvel.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Gallery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gallery_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "superhero_id", nullable = false)
	@JsonIgnore
	public SuperHero superhero;

	private String url;
	private String name;

	public Gallery() {

	}

	public Gallery(String url, String namePhoto, SuperHero superhero) {
		this.setUrl(url);
		this.setName(namePhoto);
		this.setSuperhero(superhero);
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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