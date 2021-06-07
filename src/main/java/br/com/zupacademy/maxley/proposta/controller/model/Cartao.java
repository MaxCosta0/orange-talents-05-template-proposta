package br.com.zupacademy.maxley.proposta.controller.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Cartao {

    @Id @Column(length = 50)
    private String id;
    @NotBlank
    private String emitidoEm;
    @NotBlank
    private String titular;
    @Positive
    private Integer limite;

    @Deprecated
    public Cartao(){}

    public Cartao(String id, String emitidoEm, String titular, Integer limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }
}
