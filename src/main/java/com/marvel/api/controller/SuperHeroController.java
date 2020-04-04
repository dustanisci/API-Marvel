package com.marvel.api.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.api.model.SuperHero;
import com.marvel.api.repository.SuperHeroRepository;

@RestController
@CrossOrigin
@RequestMapping("/superhero")
public class SuperHeroController {

	@Autowired
	private SuperHeroRepository superHeroRepository;

	@GetMapping
	public Page<SuperHero> findAll(Pageable pageable, @RequestParam(required = false) String search) {
		if (search != null) {
			return superHeroRepository.search(search, pageable);
		}
		return superHeroRepository.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuperHero> getEntity(@PathVariable Long id) {
		Optional<SuperHero> optional = superHeroRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(superHeroRepository.findById(id).get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody SuperHero superHero) {
		superHeroRepository.save(superHero);
		return ResponseEntity.ok().build();
	}

	@PutMapping
	@Transactional
	public ResponseEntity<?> update(@RequestBody @Valid SuperHero superHero) {
		Optional<SuperHero> optional = superHeroRepository.findById(superHero.getId());
		if (optional.isPresent()) {
			superHero.update(superHeroRepository.getOne(superHero.getId()));
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping
	public ResponseEntity<?> deleteIds(@RequestBody List<Long> ids) {
		for (long id : ids) {
			Optional<SuperHero> optional = superHeroRepository.findById(id);
			
			if (optional.isPresent()) {
				superHeroRepository.getOne(id).getGalleries().forEach((k, v) -> {
					new File(v.getUrl()).delete();
				});
				superHeroRepository.deleteById(id);
			}
		}
		return ResponseEntity.ok().build();
	}

}