package br.com.zupacademy.maxley.proposta.controller.feign;
import br.com.zupacademy.maxley.proposta.controller.dto.BloqueioCartaoRequest;
import br.com.zupacademy.maxley.proposta.controller.dto.BloqueioCartaoResponse;
import br.com.zupacademy.maxley.proposta.controller.dto.CartaoClientResponse;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, value = "")
    CartaoClientResponse criaCartao(@PathVariable("idProposta") Long idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios")
    BloqueioCartaoResponse bloqueaCartao(@PathVariable("id") String id, BloqueioCartaoRequest request);
}
