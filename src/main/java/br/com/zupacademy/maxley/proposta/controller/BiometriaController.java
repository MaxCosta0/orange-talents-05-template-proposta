package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.BiometriaRequest;
import br.com.zupacademy.maxley.proposta.model.Biometria;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class BiometriaController {

    @Autowired
    private EntityManager manager;

    @PostMapping(value = "/biometrias/cartoes/{idCartao}")
    @Transactional
    public ResponseEntity<?> criaBiometria(@PathVariable("idCartao") String idCartao,
                                           @RequestBody @Valid BiometriaRequest request,
                                           UriComponentsBuilder uriBuilder){
        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = request.toModel(cartao);
        cartao.getBiometrias().add(biometria);
        manager.persist(biometria);
        manager.merge(cartao);

        URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
