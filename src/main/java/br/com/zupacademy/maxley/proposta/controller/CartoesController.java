package br.com.zupacademy.maxley.proposta.controller;

import br.com.zupacademy.maxley.proposta.controller.dto.CartaoClientResponse;
import br.com.zupacademy.maxley.proposta.controller.dto.EstadoProposta;
import br.com.zupacademy.maxley.proposta.controller.feign.CartoesClient;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.model.Proposta;
import br.com.zupacademy.maxley.proposta.repository.CartaoRepository;
import br.com.zupacademy.maxley.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class CartoesController {

    @Autowired
    private CartoesClient cartoesClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private EntityManager manager;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void atribuirCartaoAPropostaElegivel(){
        //Encontra propostas com estado Elegivel
        List<Proposta> propostasElegiveis = propostaRepository.findByEstado(EstadoProposta.ELEGIVEL);

        for(Proposta proposta: propostasElegiveis){
            if(proposta.getCartao() == null){
                CartaoClientResponse response = cartoesClient.criaCartao(proposta.getId());
                Cartao novoCartaoProposta = response.toModel(proposta);

                proposta.setCartao(novoCartaoProposta);
                manager.merge(proposta);
            }
        }
    }
}
