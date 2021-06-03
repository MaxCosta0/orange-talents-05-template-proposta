package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.NovaPropostaRequest;
import br.com.zupacademy.maxley.proposta.controller.model.Proposta;
import br.com.zupacademy.maxley.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PropostasController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping(value = "/propostas")
    @Transactional
    public ResponseEntity<?> cadastrarProposta(@Valid @RequestBody NovaPropostaRequest request,
                                               UriComponentsBuilder uriComponentsBuilder){

       Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(request.getDocumento());



       if(possivelProposta.isPresent()){
           throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento ja cadastrado");
       }

       Proposta novaProposta = request.toModel();
       manager.persist(novaProposta);
       URI urlProposta = uriComponentsBuilder.path("propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
       return ResponseEntity.created(urlProposta).build();
    }
}
