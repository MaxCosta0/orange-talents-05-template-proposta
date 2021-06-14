package br.com.zupacademy.maxley.proposta.controller.feign;
import br.com.zupacademy.maxley.proposta.controller.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, value = "")
    CartaoClientResponse criaCartao(@PathVariable("idProposta") Long idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios")
    BloqueioCartaoResponse bloqueaCartao(@PathVariable("id") String id, BloqueioCartaoRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/{idCartao}/avisos")
    AvisoViagemResponse notificaViagemSistemaBancario(@PathVariable("idCartao") String idCartao, AvisoViagemRequest request);
}
