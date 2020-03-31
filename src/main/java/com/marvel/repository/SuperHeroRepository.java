package com.marvel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvel.model.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
		
}
