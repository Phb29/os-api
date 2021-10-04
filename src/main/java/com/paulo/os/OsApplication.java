package com.paulo.os;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paulo.os.domain.Cliente;
import com.paulo.os.domain.OS;
import com.paulo.os.domain.Tecnico;
import com.paulo.os.domain.enus.Prioridade;
import com.paulo.os.domain.enus.Status;
import com.paulo.os.repositories.ClienteRepository;
import com.paulo.os.repositories.OSRepository;
import com.paulo.os.repositories.TecnicoRepository;

@SpringBootApplication
public class OsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsApplication.class, args);
	}

}