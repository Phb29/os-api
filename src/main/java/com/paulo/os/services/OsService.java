package com.paulo.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.os.domain.Cliente;
import com.paulo.os.domain.OS;
import com.paulo.os.domain.Tecnico;
import com.paulo.os.domain.enus.Prioridade;
import com.paulo.os.domain.enus.Status;
import com.paulo.os.dtos.OSDTO;
import com.paulo.os.repositories.OSRepository;
import com.paulo.os.services.exception.ObjectNotFoundException;

@Service
public class OsService {
	
	@Autowired
	private  OSRepository repository;
	
	@Autowired
	private  TecnicoService tecnicoService; 
	@Autowired
	private  ClienteService clienteService; 
	
	public OS findByid(Integer id) {
		Optional<OS> obj= repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id:" +id+ ", tipo:"+ OS.class.getName()));
	}

	public  List<OS>findAll(){
		return  repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	public OS update(@Valid OSDTO obj) {
		findByid(obj.getId());
		return fromDTO(obj);
	}
	private  OS fromDTO(OSDTO obj) {
		OS newObj= new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec= tecnicoService.findByid(obj.getTecnico());
		Cliente cli= clienteService.findByid(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
			
		}
		return repository.save(newObj);
	}

	
}
