package com.aluracursos.literalurav2;

import com.aluracursos.literalurav2.principal.Principal;
import com.aluracursos.literalurav2.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Literalurav2Application implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;//inyección de dependencias

	public static void main(String[] args) {
		SpringApplication.run(Literalurav2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.mostarMenu();

	}
}
