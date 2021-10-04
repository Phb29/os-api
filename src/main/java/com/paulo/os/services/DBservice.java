package com.paulo.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.os.domain.Cliente;
import com.paulo.os.domain.OS;
import com.paulo.os.domain.Tecnico;
import com.paulo.os.domain.enus.Prioridade;
import com.paulo.os.domain.enus.Status;
import com.paulo.os.repositories.ClienteRepository;
import com.paulo.os.repositories.OSRepository;
import com.paulo.os.repositories.TecnicoRepository;

@Service
public class DBservice {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	
	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "valdir cezar", "022.720.480-80", "(88)98454-5731");
		Tecnico t2= new Tecnico(null, "carlos", "764.310.050-07", "(88)984153866");
		Cliente c1 = new Cliente(null, "betina campos", "771.546.970-08", "(62)9922-22567");
		OS os1 = new OS(null, Prioridade.ALTA, "teste create os", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1,t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}
