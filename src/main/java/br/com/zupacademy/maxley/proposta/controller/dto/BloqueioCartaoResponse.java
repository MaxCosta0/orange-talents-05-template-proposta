package br.com.zupacademy.maxley.proposta.controller.dto;

import javax.validation.constraints.NotBlank;

public class BloqueioCartaoResponse {

    @NotBlank
    private String resultado;

    @Deprecated
    public BloqueioCartaoResponse(){}

    public String getResultado() {
        return resultado;
    }
}
