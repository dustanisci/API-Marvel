package com.marvel.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.model.SuperHero;
import com.marvel.repository.SuperHeroRepository;

@RestController
@RequestMapping("/")
public class SuperHeroController {

	@Autowired
	private SuperHeroRepository superHeroRepository;
	
	@GetMapping
	public List<SuperHero> findAllByNameAsc() {
		return superHeroRepository.findAll();
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
	public ResponseEntity<?> insert(@RequestBody @Valid SuperHero superHero){
		superHeroRepository.save(superHero);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid SuperHero superHeroReceived) {
		Optional<SuperHero> optional = superHeroRepository.findById(id);
		if (optional.isPresent()) {
			superHeroReceived.update(id, superHeroRepository);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteId(@PathVariable Long id) {
		Optional<SuperHero> optional = superHeroRepository.findById(id);
		if (optional.isPresent()) {
			superHeroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@DeleteMapping("/{ids}")
	public ResponseEntity<?> deleteIds(@PathVariable List<String> ids) {
		
		for(String id : ids) {
			Optional<SuperHero> optional = superHeroRepository.findById(Long.parseLong(id));
			
			if (optional.isPresent()) {
				superHeroRepository.deleteById(Long.parseLong(id));
			}
		}
		
		return ResponseEntity.ok().build();
	}
	
	
}
