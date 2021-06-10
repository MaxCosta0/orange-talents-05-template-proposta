package br.com.zupacademy.maxley.proposta.repository;

import br.com.zupacademy.maxley.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
