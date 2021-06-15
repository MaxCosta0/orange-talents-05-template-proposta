package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.BloqueioCartaoRequest;
import br.com.zupacademy.maxley.proposta.controller.dto.BloqueioCartaoResponse;
import br.com.zupacademy.maxley.proposta.controller.dto.EstadoCartao;
import br.com.zupacademy.maxley.proposta.controller.feign.CartoesClient;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.model.DadosBloqueioCartao;
import br.com.zupacademy.maxley.proposta.repository.BloqueioRepository;
import br.com.zupacademy.maxley.proposta.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class BloqueioCartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    BloqueioRepository bloqueioRepository;

    @Autowired
    private CartoesClient cartoesClient;

    @PostMapping(value = "/cartoes/bloquear/{id}")
    //@Transactional
    public ResponseEntity<?> bloquear(@PathVariable("id") String id,
                                      @RequestHeader("User-Agent") String userAgent,
                                      HttpServletRequest request) throws IOException {
        //Cartao cartao = manager.find(Cartao.class, id);
        Cartao cartao = cartaoRepository.findById(id).get();

        //Caso onde o cartao nao é encontrado
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        //Caso onde o cartao ja esta bloqueado
        if(cartao.getEstadoCartao() == EstadoCartao.BLOQUEADO){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        boolean bloqueadoNoSistemaLegado = bloqueioNoSistemaLegado(cartao);

        //Caso onde o sistema legado nao pode bloquear o cartao por algum motivo
        if(!bloqueadoNoSistemaLegado){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        DadosBloqueioCartao bloqueio = new DadosBloqueioCartao(request.getRemoteAddr(), userAgent);
        cartao.setBloqueio(bloqueio);

        //manager.persist(bloqueio);
        //manager.merge(cartao);

        bloqueioRepository.save(bloqueio);
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }

    public boolean bloqueioNoSistemaLegado(Cartao cartao) throws IOException {
        try{
            BloqueioCartaoResponse response = cartoesClient.bloqueaCartao(cartao.getId(), new BloqueioCartaoRequest("Proposta"));
            cartao.setEstadoCartao(EstadoCartao.BLOQUEADO);
        }catch (FeignException exception) {
            return false;
        }
        return true;
    }
}
