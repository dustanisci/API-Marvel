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
	private Long id;
	
	@NotNull
	@NotEmpty
	@Column(name = "name")
	private String name;
	
	@Column(name = "codename")
	private String codename;
	
	@Column(name = "earth")
	private String earth;
	
	@Column(name = "job")
	private String job;
	
	@Column(name = "genealogy")
	private String genealogy;
	
	@Column(name = "race")
	private String race;
	
	@Column(name = "team")
	private String team;
	
	@Column(name = "first_show")
	private String firstShow;
	
	public Long getId() {
		return id;
	}
	
	@OneToMany(mappedBy="superhero", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(name="gallery")
	@OrderBy("id asc")
	@MapKey(name="id")
	private Map<Long, Gallery> galleries = new HashMap<>();
	
	public Map<Long, Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(Map<Long, Gallery> galleries) {
		this.galleries = galleries;
	}
	
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