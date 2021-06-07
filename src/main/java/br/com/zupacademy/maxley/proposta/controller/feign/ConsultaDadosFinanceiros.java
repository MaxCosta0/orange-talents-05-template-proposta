package br.com.zupacademy.maxley.proposta.controller.feign;

import br.com.zupacademy.maxley.proposta.controller.dto.VerificaPropostaRequest;
import br.com.zupacademy.maxley.proposta.controller.dto.VerificaPropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "verifica-proposta", url = "http://localhost:9999/api/solicitacao")
public interface ConsultaDadosFinanceiros {
    @RequestMapping(method = RequestMethod.POST, value = "")
    VerificaPropostaResponse getResponse(VerificaPropostaRequest propostaASerVerificada);
}
