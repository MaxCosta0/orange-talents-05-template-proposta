package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.NovaPropostaRequest;
import br.com.zupacademy.maxley.proposta.controller.model.Proposta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostasController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/propostas")
    @Transactional
    public ResponseEntity<?> cadastrarProposta(@Valid @RequestBody NovaPropostaRequest request,
                                               UriComponentsBuilder uriComponentsBuilder){
       Proposta novaProposta = request.toModel();
       manager.persist(novaProposta);
       URI urlProposta = uriComponentsBuilder.path("propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
       return ResponseEntity.created(urlProposta).build();
    }
}
