package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.AvisoViagemRequest;
import br.com.zupacademy.maxley.proposta.model.AvisoViagem;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    @Autowired
    private EntityManager manager;

    @PostMapping(value = "/cartoes/{idCartao}/viagens")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable("idCartao") String idCartao,
                                       @RequestHeader("User-Agent") String userAgent,
                                       HttpServletRequest httpServletRequest,
                                       @Valid @RequestBody AvisoViagemRequest request){
        Cartao cartao = manager.find(Cartao.class, idCartao);

        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        AvisoViagem avisoViagem = request.toModel(cartao, userAgent, httpServletRequest.getRemoteAddr());
        manager.persist(avisoViagem);

        return ResponseEntity.ok().build();
    }
}
