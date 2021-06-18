package br.com.zupacademy.maxley.proposta;

import br.com.zupacademy.maxley.proposta.controller.dto.DocumentoLimpo;
import br.com.zupacademy.maxley.proposta.controller.dto.EstadoProposta;
import br.com.zupacademy.maxley.proposta.model.Proposta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableWebSecurity
public class PropostaApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager manager;

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Proposta proposta1 = new Proposta(new DocumentoLimpo("225.534.860-81"), "maxley1@mail.com",  "maxley1", "Avenida A", new BigDecimal("2500"));
		Proposta proposta2 = new Proposta(new DocumentoLimpo("756.117.450-08"), "maxley2@mail.com",  "maxley2", "Avenida B", new BigDecimal("2500"));
		Proposta proposta3 = new Proposta(new DocumentoLimpo("343.199.760-02"), "maxley3@mail.com",  "maxley3", "Avenida C", new BigDecimal("2500"));
		Proposta proposta4 = new Proposta(new DocumentoLimpo("720.306.100-03"), "maxley4@mail.com",  "maxley4", "Avenida D", new BigDecimal("2500"));
		Proposta proposta5 = new Proposta(new DocumentoLimpo("349.787.820-07"), "maxley5@mail.com",  "maxley5", "Avenida E", new BigDecimal("2500"));
		proposta1.setEstado(EstadoProposta.ELEGIVEL);
		proposta2.setEstado(EstadoProposta.ELEGIVEL);
		proposta3.setEstado(EstadoProposta.NAO_ELEGIVEL);
		manager.persist(proposta1);
		manager.persist(proposta2);
		manager.persist(proposta3);
		manager.persist(proposta4);
		manager.persist(proposta5);

//		String documento = "486.838.680-87";
//
//		String secret;
//		String salt;
//
//		TextEncryptor encryptor = Encryptors.text(secret, salt);
//		String criptografado = encryptor.encrypt(documento);
//
//		String descriptografado = encryptor.decrypt(criptografado);
//
//		System.out.println("Criptografia: " + criptografado);
//		System.out.println("Descriptografia: " + descriptografado);
	}
}
