package br.com.zupacademy.maxley.proposta.controller.dto;

public class BloqueioCartaoRequest {

    private String sistemaResponsavel;

    @Deprecated
    public BloqueioCartaoRequest(){}

    public BloqueioCartaoRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
