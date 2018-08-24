package com.nelioalves.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursospring.domain.Categoria;
import com.nelioalves.cursospring.repositories.CategoriaRepository;
import com.nelioalves.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));		
	}
	
}
