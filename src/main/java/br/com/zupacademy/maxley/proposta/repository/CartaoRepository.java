package br.com.zupacademy.maxley.proposta.repository;

import br.com.zupacademy.maxley.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findById(String id);
}
