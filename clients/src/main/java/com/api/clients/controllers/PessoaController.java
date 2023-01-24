package com.api.clients.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.clients.entities.Pessoa;
import com.api.clients.repositories.PesssoaRepository;

@RestController
@RequestMapping(value = "/pessoa" )
public class PessoaController {
	
	@Autowired
	private PesssoaRepository repository;
	
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> result = repository.findAll();
		if(result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(result);
		
	}
	
	
	
	@PostMapping
	public ResponseEntity<Pessoa> createPeople(@RequestBody Pessoa pessoa){
		try{
			Pessoa result = repository.save(pessoa);
			return ResponseEntity.status(201).body(result);
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		
		
		
		
	}
	
	
	@DeleteMapping
	public ResponseEntity<String> deletePeople(@RequestBody Pessoa pessoa ){
		 try {
			 repository.deleteById(pessoa.getId());
			 return ResponseEntity.ok("sucesso");
				
		 }catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> putPeople(@RequestBody Pessoa pessoa ){
		 Optional<Pessoa> pessoaFind= repository.findById(pessoa.getId());
		 return pessoaFind.map(item->{
			 item.setDataNac(pessoa.getDataNac());
			 item.setEndereco(pessoa.getEndereco());
			 item.setName(pessoa.getName());
			 repository.save(item);
			 return ResponseEntity.ok().body(pessoa);
			 
		 }).orElse(ResponseEntity.notFound().build());	
		
	}
	
	

}
