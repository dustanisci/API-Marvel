package com.marvel.api.model;

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

@Entity
public class SuperHero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "superhero_id")
	private long id;

	@OneToMany(mappedBy = "superhero", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(name = "gallery")
	@OrderBy("id asc")
	@MapKey(name = "id")
	private Map<Long, Gallery> galleries = new HashMap<>();

	@NotNull
	@NotEmpty
	@Column(length = 3000)
	private String name;

	@Column(length = 3000)
	private String codename;
	
	private String earth;
	
	@Column(length = 3000)
	private String job;
	
	@Column(length = 3000) 
	private String genealogy;
	
	private String race;
	
	@Column(length = 3000)
	private String team;
	
	@Column(length = 3000)
	private String firstShow;

	public SuperHero() {

	}

	public SuperHero(String name, String codename, String earth, String job, String genealogy, String race, String team,
			String firstShow) {
		this.setName(name);
		this.setCodename(codename);
		this.setEarth(earth);
		this.setJob(job);
		this.setGenealogy(genealogy);
		this.setRace(race);
		this.setTeam(team);
		this.setFirstShow(firstShow);
	}

	public SuperHero(long id) {
		this.setId(id);
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGalleries(Map<Long, Gallery> galleries) {
		this.galleries = galleries;
	}

	public Map<Long, Gallery> getGalleries() {
		return galleries;
	}

	public void update(SuperHero superHero) {
		superHero.setCodename(this.codename);
		superHero.setEarth(this.earth);
		superHero.setFirstShow(this.firstShow);
		superHero.setGenealogy(this.genealogy);
		superHero.setJob(this.job);
		superHero.setName(this.name);
		superHero.setRace(this.race);
		superHero.setTeam(this.team);
	}

}