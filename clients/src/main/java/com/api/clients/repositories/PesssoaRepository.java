package com.api.clients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.clients.entities.Pessoa;

public interface PesssoaRepository extends JpaRepository<Pessoa, Long> {

}
