package br.com.zupacademy.maxley.proposta.controller.dto;

import javax.validation.constraints.NotBlank;

public class AvisoViagemResponse {

    @NotBlank
    String resultado;

    @Deprecated
    public AvisoViagemResponse(){}

    public AvisoViagemResponse(String resultado) {
        this.resultado = resultado;
    }
}
