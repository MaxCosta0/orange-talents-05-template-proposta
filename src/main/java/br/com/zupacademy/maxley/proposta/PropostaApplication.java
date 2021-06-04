package br.com.zupacademy.maxley.proposta;

import br.com.zupacademy.maxley.proposta.controller.model.Proposta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class PropostaApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager manager;

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Proposta proposta = new Proposta("225.534.860-81", "maxley@mail.com",  "maxley", "Avenida A", new BigDecimal("2500"));
		manager.persist(proposta);
	}
}
