package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.model.DadosBloqueioCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
public class BloqueioCartaoController {

    @Autowired
    private EntityManager manager;

    @PostMapping(value = "/cartoes/bloquear/{id}")
    @Transactional
    public ResponseEntity<?> bloquear(@PathVariable("id") String id,
                                      @RequestHeader("User-Agent") String userAgent,
                                      HttpServletRequest request){
        Cartao cartao = manager.find(Cartao.class, id);

        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        if(cartao.getBloqueio() != null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        DadosBloqueioCartao bloqueio = new DadosBloqueioCartao(request.getRemoteAddr(), userAgent);
        cartao.setBloqueio(bloqueio);
        manager.persist(bloqueio);
        manager.merge(cartao);

        return ResponseEntity.ok().build();
    }
}
