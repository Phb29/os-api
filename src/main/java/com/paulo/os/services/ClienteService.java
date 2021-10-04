package com.paulo.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.os.domain.Cliente;
import com.paulo.os.domain.Pessoa;
import com.paulo.os.dtos.ClienteDTO; 
import com.paulo.os.repositories.ClienteRepository;
import com.paulo.os.repositories.PessoaRepository;
import com.paulo.os.services.exception.DataintegratyViolationexception;
import com.paulo.os.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findByid(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ",tipo:" + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {

		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataintegratyViolationexception("cpf ja cadastrado na base");
		}

		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getEmail()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findByid(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataintegratyViolationexception("cpf ja cadastrado na base");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setEmail(objDTO.getEmail());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
	Cliente obj=	findByid(id);
	if(obj.getList().size()>0) {
		throw new DataintegratyViolationexception("pessoa possui ordem de serviço,não pode ser deletado");
		
	}
	repository.deleteById(id);

	}

	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
