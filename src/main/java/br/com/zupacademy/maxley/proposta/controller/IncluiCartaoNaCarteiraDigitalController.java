package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.CarteiraDigitalRequest;
import br.com.zupacademy.maxley.proposta.controller.feign.CartoesClient;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.model.CarteiraDigital;
import br.com.zupacademy.maxley.proposta.repository.CartaoRepository;
import br.com.zupacademy.maxley.proposta.repository.CarteiraDigitalRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class IncluiCartaoNaCarteiraDigitalController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;

    @Autowired
    private CartoesClient cartoesClient;

    @PostMapping(value = "/cartoes/{idCartao}/carteira-digital")
    public ResponseEntity<?> incluirCartaoNoPaypal(@PathVariable("idCartao") String idCartao,
                                                   @Valid @RequestBody CarteiraDigitalRequest request,
                                                   UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);

        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        try{
            cartoesClient.associaCartaoACarteiraDigitalNoSistemaBancario(idCartao, request);
        }catch (FeignException exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        CarteiraDigital carteiraDigital = request.toModel(possivelCartao.get());
        carteiraDigitalRepository.save(carteiraDigital);

        URI uri = uriComponentsBuilder.path("/carteiras-digital/{ìd}").buildAndExpand(carteiraDigital.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
