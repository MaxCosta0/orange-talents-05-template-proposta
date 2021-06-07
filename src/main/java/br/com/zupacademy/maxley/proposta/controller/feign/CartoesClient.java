package br.com.zupacademy.maxley.proposta.controller.feign;

import br.com.zupacademy.maxley.proposta.controller.dto.CartaoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, value = "")
    CartaoClientResponse criaCartao(@RequestParam("idProposta") Long idProposta);
}
