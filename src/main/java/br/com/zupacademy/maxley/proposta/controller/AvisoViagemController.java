package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.AvisoViagemRequest;
import br.com.zupacademy.maxley.proposta.controller.feign.CartoesClient;
import br.com.zupacademy.maxley.proposta.model.AvisoViagem;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AvisoViagemController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private CartoesClient cartoesClient;

    @PostMapping(value = "/cartoes/{idCartao}/viagens")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable("idCartao") String idCartao,
                                       @RequestHeader("User-Agent") String userAgent,
                                       HttpServletRequest httpServletRequest,
                                       @Valid @RequestBody AvisoViagemRequest request){
        Cartao cartao = manager.find(Cartao.class, idCartao);

        //Caso o cartao nao exista no banco de dados
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        boolean notificaSistemBancario = notificaAvisoViagemASistemaBancario(idCartao, request);

        //Caso o aviso não seja reconhecido pelo sistema bancario, entao quero que o usuario
        //fique sabendo que a tentativa nao foi bem sucedida.
        if(!notificaSistemBancario){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        AvisoViagem avisoViagem = request.toModel(cartao, userAgent, httpServletRequest.getRemoteAddr());
        manager.persist(avisoViagem);

        return ResponseEntity.ok().build();
    }

    public boolean notificaAvisoViagemASistemaBancario(String idCartao, AvisoViagemRequest request){
        try{
            cartoesClient.notificaViagemSistemaBancario(idCartao, request);
        }catch(FeignException exception){
            return false;
        }
        return true;
    }
}