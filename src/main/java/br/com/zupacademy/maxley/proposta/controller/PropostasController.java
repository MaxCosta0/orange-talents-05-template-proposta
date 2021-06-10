package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.*;
import br.com.zupacademy.maxley.proposta.controller.feign.ConsultaDadosFinanceiros;
import br.com.zupacademy.maxley.proposta.model.Proposta;
import br.com.zupacademy.maxley.proposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private ConsultaDadosFinanceiros consulta;

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

    @GetMapping(value = "/verifica-proposta/{id}")
    @Transactional
    public ResponseEntity<?> verificaProposta(@PathVariable("id") Long id){
        Proposta propostaASerVerificada = manager.find(Proposta.class, id);

        if(propostaASerVerificada == null){
            return ResponseEntity.notFound().build();
        }

        VerificaPropostaRequest propostaRequest = new VerificaPropostaRequest(propostaASerVerificada);
        try{
            VerificaPropostaResponse response = consulta.getResponse(propostaRequest);
            propostaASerVerificada.setEstado(EstadoProposta.ELEGIVEL);
            manager.merge(propostaASerVerificada);
            return ResponseEntity.ok().build();
        }catch (FeignException exception){
            propostaASerVerificada.setEstado(EstadoProposta.NAO_ELEGIVEL);
            manager.merge(propostaASerVerificada);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    public ResponseEntity<PropostaResponse> detalharProposta(@PathVariable("id") Long id){
        Proposta possivelProposta = manager.find(Proposta.class, id);

        if(possivelProposta == null){
            return ResponseEntity.notFound().build();
        }

        PropostaResponse response = new PropostaResponse(possivelProposta.getEstado());
        return ResponseEntity.ok(response);
    }
}
