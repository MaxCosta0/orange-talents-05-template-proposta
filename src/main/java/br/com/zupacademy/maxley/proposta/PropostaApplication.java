package br.com.zupacademy.maxley.proposta;

import br.com.zupacademy.maxley.proposta.model.Proposta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class PropostaApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager manager;

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Proposta proposta1 = new Proposta("225.534.860-81", "maxley1@mail.com",  "maxley1", "Avenida A", new BigDecimal("2500"));
		Proposta proposta2 = new Proposta("756.117.450-08", "maxley2@mail.com",  "maxley2", "Avenida B", new BigDecimal("2500"));
		Proposta proposta3 = new Proposta("343.199.760-02", "maxley3@mail.com",  "maxley3", "Avenida C", new BigDecimal("2500"));
		Proposta proposta4 = new Proposta("720.306.100-03", "maxley4@mail.com",  "maxley4", "Avenida D", new BigDecimal("2500"));
		Proposta proposta5 = new Proposta("349.787.820-07", "maxley5@mail.com",  "maxley5", "Avenida E", new BigDecimal("2500"));
		manager.persist(proposta1);
		manager.persist(proposta2);
		manager.persist(proposta3);
		manager.persist(proposta4);
		manager.persist(proposta5);
	}
}
