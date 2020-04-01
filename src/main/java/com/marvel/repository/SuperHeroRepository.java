package com.marvel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marvel.model.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
	
	@Query(	"FROM SuperHero s " + 
			"WHERE LOWER(s.name) like %:search% " +
			"OR LOWER(s.codename) like %:search%")
	Page<SuperHero> search(@Param("search") String search, Pageable pageable);
		
}
