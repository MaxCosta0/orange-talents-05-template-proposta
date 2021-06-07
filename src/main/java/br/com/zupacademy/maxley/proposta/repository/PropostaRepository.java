package br.com.zupacademy.maxley.proposta.repository;

import br.com.zupacademy.maxley.proposta.controller.dto.EstadoProposta;
import br.com.zupacademy.maxley.proposta.controller.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);
    List<Proposta> findByEstado(EstadoProposta estado);
}
