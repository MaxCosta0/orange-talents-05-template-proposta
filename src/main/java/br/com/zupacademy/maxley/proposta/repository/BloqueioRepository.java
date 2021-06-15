package br.com.zupacademy.maxley.proposta.repository;

import br.com.zupacademy.maxley.proposta.model.DadosBloqueioCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<DadosBloqueioCartao, Long> {
}
