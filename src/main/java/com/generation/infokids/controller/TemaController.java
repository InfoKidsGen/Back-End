package com.generation.infokids.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.infokids.model.Tema;
import com.generation.infokids.repository.TemaRepository;


@RestController
@RequestMapping("/temas")
@CrossOrigin(origins="*", allowedHeaders="*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;
	
	public TemaRepository getTemaRepository() {
		return temaRepository;
	}

	public void setTemaRepository(TemaRepository temaRepository) {
		this.temaRepository = temaRepository;
	}

	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id) {
		return temaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/assunto/{assunto}")
	public ResponseEntity<List<Tema>> getByAssunto(@PathVariable String assunto) {
		return ResponseEntity.ok(temaRepository.findAllByAssuntoContainingIgnoreCase(assunto));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@Valid @RequestBody Tema tema) {
		return temaRepository.findById(tema.getId())
				.map(resp -> ResponseEntity.ok().body(temaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable long id) {
		return temaRepository.findById(id)
				.map(resp -> {
					temaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}			)
				.orElse(ResponseEntity.notFound().build());
	}
 }
