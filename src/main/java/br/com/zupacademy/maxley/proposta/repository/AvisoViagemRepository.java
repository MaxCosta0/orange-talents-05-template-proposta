package br.com.zupacademy.maxley.proposta.repository;

import br.com.zupacademy.maxley.proposta.model.AvisoViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
