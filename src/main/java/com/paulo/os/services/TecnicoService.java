package com.paulo.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.os.domain.Pessoa;
import com.paulo.os.domain.Tecnico;
import com.paulo.os.dtos.TecnicoDTO;
import com.paulo.os.repositories.PessoaRepository;
import com.paulo.os.repositories.TecnicoRepository;
import com.paulo.os.services.exception.DataintegratyViolationexception;
import com.paulo.os.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findByid(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ",tipo:" + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {

		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataintegratyViolationexception("cpf ja cadastrado na base");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getEmail()));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findByid(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataintegratyViolationexception("cpf ja cadastrado na base");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setEmail(objDTO.getEmail());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
	Tecnico obj=	findByid(id);
	if(obj.getList().size()>0) {
		throw new DataintegratyViolationexception("tecnico possui ordem de serviço,não pode ser deletado");
		
	}
	repository.deleteById(id);

	}

	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
