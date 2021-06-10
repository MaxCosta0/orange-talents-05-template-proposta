package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.config.annotation.CPForCNPJ;
import br.com.zupacademy.maxley.proposta.model.Proposta;

import javax.validation.constraints.NotBlank;

public class VerificaPropostaRequest {

    @NotBlank @CPForCNPJ
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    @Deprecated
    public VerificaPropostaRequest(){}

    public VerificaPropostaRequest(Proposta propostaASerVerificada) {
        this.documento = propostaASerVerificada.getDocumento();
        this.nome = propostaASerVerificada.getNome();
        this.idProposta = propostaASerVerificada.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
