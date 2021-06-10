package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.model.Cartao;

public class CartaoClientResponse {

    private String id;
    private String emitidoEm;
    private String titular;
    private Integer limite;
    private String idProposta;

    public CartaoClientResponse(String id, String emitidoEm, String titular, Integer limite, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Integer getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public Cartao toModel(){
        //Optional<Proposta> propostaAReceberCartao = propostaRepository.findById(Long.parseLong(idProposta));
       // Assert.state(propostaAReceberCartao.isPresent(), "[BUG] Id nulo para a proposta que vai receber o cartao");
        return new Cartao(id, emitidoEm, titular, limite);
    }
}
