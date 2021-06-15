package br.com.zupacademy.maxley.proposta.repository;

import br.com.zupacademy.maxley.proposta.model.CarteiraDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
}
