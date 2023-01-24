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

import com.api.clients.entities.Endereco;
import com.api.clients.repositories.EnderecoRepository;

@RestController
@RequestMapping(value = "/endereco" )
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository repository;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
		List<Endereco> result = repository.findAll();
		if(result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
			
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping
	public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco){
		try {
			Endereco result = repository.save(endereco);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		
		
		
		
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletePeople(@RequestBody Endereco endereco ){
		  try{
			  repository.deleteById(endereco.getId());
			  return ResponseEntity.ok("sucesso");
		  } catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		
	}
	
	@PutMapping
	public ResponseEntity<Endereco> putPeople(@RequestBody Endereco endereco ){
		 Optional<Endereco> enderecoFind= repository.findById(endereco.getId());
		  return enderecoFind.map(item->{
			 item.setCep(endereco.getCep());
			 item.setCidade(endereco.getCidade());
			 item.setLougadouro(endereco.getLougadouro());
			 item.setNumero(endereco.getCep());
			 repository.save(item);
			 return ResponseEntity.ok().body(item);
				
			 
		 }).orElse(ResponseEntity.notFound().build());
		 
		 
		
	}

}
