package com.nelioalves.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursospring.domain.Cliente;
import com.nelioalves.cursospring.domain.enums.TipoCliente;
import com.nelioalves.cursospring.dto.ClienteNewDTO;
import com.nelioalves.cursospring.repositories.ClienteRepository;
import com.nelioalves.cursospring.resources.exception.FieldMessage;
import com.nelioalves.cursospring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null)
			list.add(new FieldMessage("email", "Email já existente"));

		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
