package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.config.annotation.CPForCNPJ;

import javax.validation.constraints.NotBlank;

public class VerificaPropostaResponse {

    @NotBlank
    @CPForCNPJ
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    private EstadoProposta resultadoSolicitacao;

    public VerificaPropostaResponse(String documento, String nome, String idProposta, String resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;

        if(resultadoSolicitacao.equals("SEM_RESTRICAO"))
            this.resultadoSolicitacao = EstadoProposta.ELEGIVEL;
        else
            this.resultadoSolicitacao = EstadoProposta.NAO_ELEGIVEL;
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

    public EstadoProposta getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
