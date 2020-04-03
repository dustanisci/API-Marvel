package com.marvel.api.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.api.dto.SuperHeroDto;
import com.marvel.api.model.SuperHero;
import com.marvel.api.repository.SuperHeroRepository;

@RestController
@RequestMapping({ "/superhero", "/superhero/" })
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
	public ResponseEntity<?> insert(@RequestBody SuperHeroDto superHeroDto) {
		superHeroRepository.save(superHeroDto.parseToSuperHero());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Valid SuperHero superHero) {
		Optional<SuperHero> optional = superHeroRepository.findById(id);
		if (optional.isPresent()) {
			superHero.update(id, superHeroRepository);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteId(@PathVariable long id) {
		Optional<SuperHero> optional = superHeroRepository.findById(id);
		if (optional.isPresent()) {
			superHeroRepository.deleteById(id);
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
				superHeroRepository.deleteById(id);
			}
		}
		return ResponseEntity.ok().build();
	}

}