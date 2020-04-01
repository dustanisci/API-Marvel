package com.marvel.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.marvel.repository.SuperHeroRepository;

@Entity
public class SuperHero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "superhero_id")
	private long id;
	
	@OneToMany(mappedBy="superhero", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(name="gallery")
	@OrderBy("id asc")
	@MapKey(name="id")
	private Map<Long, Gallery> galleries = new HashMap<>();
	
	@NotNull
	@NotEmpty
	private String name;
	
	private String codename;
	private String earth;
	private String job;
	private String genealogy;
	private String race;
	private String team;
	private String firstShow;
			
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getEarth() {
		return earth;
	}

	public void setEarth(String earth) {
		this.earth = earth;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGenealogy() {
		return genealogy;
	}

	public void setGenealogy(String genealogy) {
		this.genealogy = genealogy;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getFirstShow() {
		return firstShow;
	}

	public void setFirstShow(String firstShow) {
		this.firstShow = firstShow;
	}

	public long getId() {
		return id;
	}

	public void setGalleries(Map<Long, Gallery> galleries) {
		this.galleries = galleries;
	}
		
	public Map<Long, Gallery> getGalleries() {
		return galleries;
	}

	public SuperHero update(Long id, SuperHeroRepository superHeroRepository) {
		SuperHero superHero = superHeroRepository.getOne(id);
		superHero.setCodename(this.codename);
		superHero.setEarth(this.earth);
		superHero.setFirstShow(this.firstShow);
		superHero.setGenealogy(this.genealogy);
		superHero.setJob(this.job);
		superHero.setName(this.name);
		superHero.setRace(this.race);
		superHero.setTeam(this.team);
		return superHero;
	}
	
}
