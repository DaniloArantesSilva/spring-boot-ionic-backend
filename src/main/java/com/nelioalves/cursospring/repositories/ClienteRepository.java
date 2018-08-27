package com.nelioalves.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursospring.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	

}
