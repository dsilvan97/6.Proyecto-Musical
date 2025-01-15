package com.biogade.proyectomusical;

import com.biogade.proyectomusical.principal.Principal;
import com.biogade.proyectomusical.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoMusicalApplication implements CommandLineRunner {

	@Autowired
	private CantanteRepository cantanteRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoMusicalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(cantanteRepository);

		principal.mostrarMenu();
	}
}
