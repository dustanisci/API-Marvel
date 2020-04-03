package com.marvel.api.dto;

import java.util.HashMap;
import java.util.Map;

import com.marvel.api.model.Gallery;
import com.marvel.api.model.SuperHero;

public class SuperHeroDto {

	private long id;
	private Map<Long, Gallery> galleries = new HashMap<>();
	private String name;
	private String codename;
	private String earth;
	private String job;
	private String genealogy;
	private String race;
	private String team;
	private String firstShow;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public SuperHero parseToSuperHero() {
		return new SuperHero(name, codename, earth, job, genealogy, race, team, firstShow);
	}

}
